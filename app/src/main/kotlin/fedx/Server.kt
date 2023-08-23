package fedx

import java.io.File
import org.eclipse.rdf4j.federated.FedXFactory
import org.eclipse.rdf4j.federated.repository.FedXRepository
import org.eclipse.rdf4j.http.server.readonly.QueryResponder
import org.eclipse.rdf4j.repository.Repository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

@SpringBootApplication
@ComponentScan(basePackages = ["org.eclipse.rdf4j", "fedx"])
@Import(
    QueryResponder::class,
)
open class Server(@Value("\${application.federation.configPath}") private val configPath: String) {
  private lateinit var repository: Repository

  @Bean
  open fun getRepository(): Repository {
    if (!::repository.isInitialized) {
      val file = File(configPath)
      val fedxRepository: FedXRepository = FedXFactory.createFederation(file)
      fedxRepository.init()
      val context = fedxRepository.getFederationContext()
      context.config.withLogQueries(true).withEnableMonitoring(true).withEnforceMaxQueryTime(300)
      repository = fedxRepository
    }

    return repository
  }

  companion object {
    fun main(args: Array<String>) {
      SpringApplication.run(Server::class.java, *args)
    }
  }
}
