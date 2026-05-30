package com.smartparking.smartparking.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Desktop;
import java.net.URI;



@Configuration
public class BrowserLauncher {

    @Bean
    public CommandLineRunner openBrowserOnStartup() {
        return args -> {
            try {
                Thread.sleep(3000);
                String url = "http://localhost:8090/";

                // Try using Desktop first
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI(url));
                } else {
                    // Fallback: OS-based command
                    String os = System.getProperty("os.name").toLowerCase();
                    Runtime runtime = Runtime.getRuntime();

                    if (os.contains("win")) {
                        runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                    } else if (os.contains("mac")) {
                        runtime.exec("open " + url);
                    } else if (os.contains("nix") || os.contains("nux")) {
                        runtime.exec("xdg-open " + url);
                    } else {
                        System.out.println("Unsupported OS. Please open manually: " + url);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error while trying to open browser: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
