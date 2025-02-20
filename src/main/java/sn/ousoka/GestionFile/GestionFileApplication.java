package sn.ousoka.GestionFile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import sn.ousoka.GestionFile.model.Location;
import sn.ousoka.GestionFile.model.OKService;
import sn.ousoka.GestionFile.repository.LocationRepository;
import sn.ousoka.GestionFile.repository.ServiceRepository;

@SpringBootApplication
public class GestionFileApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GestionFileApplication.class, args);
    }

    // Override the configure method for servlet container initialization
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GestionFileApplication.class);
    }

    // Seed the database if it's empty
    @Bean
    public CommandLineRunner seedDatabase(ServiceRepository serviceRepository, LocationRepository locationRepository) {
        return args -> {

            // Check and seed locations if not already present
            if (locationRepository.count() == 0) {
                Location dakar = new Location();
                dakar.setName("Dakar");
                locationRepository.save(dakar);

                Location thies = new Location();
                thies.setName("Thies");
                locationRepository.save(thies);

                Location matam = new Location();
                matam.setName("Matam");
                locationRepository.save(matam);

                System.out.println("Emplacements pour les localisations prédéfinis avec succès!");
            } else {
                System.out.println("Des emplacements existent déjà. Sauter l'ensemencement");
            }

            // Check and seed services if not already present
            if (serviceRepository.count() == 0) {
                OKService senelec = new OKService();
                senelec.setName("Senelec");
                serviceRepository.save(senelec);

                OKService seneau = new OKService();
                seneau.setName("Seneau");
                serviceRepository.save(seneau);

                OKService banque = new OKService();
                banque.setName("Banque");
                serviceRepository.save(banque);

                System.out.println("Emplacements pour les Services prédéfinis avec succès");
            } else {
                System.out.println("Les Services existent déjà. Sauter l'ensemencement.");
            }
        };
    }
}
