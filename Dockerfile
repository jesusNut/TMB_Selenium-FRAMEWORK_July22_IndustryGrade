FROM jesusnut/java11-mvn3.6.3-all_dependencies
COPY src  home/TMB_Selenium-FRAMEWORK_July22/src
COPY pom.xml home/TMB_Selenium-FRAMEWORK_July22/pom.xml
COPY extent-test-output home/TMB_Selenium-FRAMEWORK_July22/extent-test-output
COPY print-page-to-pdf-output home/TMB_Selenium-FRAMEWORK_July22/print-page-to-pdf-output
COPY test-output home/TMB_Selenium-FRAMEWORK_July22/print-page-to-pdf-output
COPY executeTests.sh home/TMB_Selenium-FRAMEWORK_July22/executeTests.sh
RUN chmod +x home/TMB_Selenium-FRAMEWORK_July22/executeTests.sh
WORKDIR /home/TMB_Selenium-FRAMEWORK_July22
ENTRYPOINT ["/bin/bash","./executeTests.sh"]
