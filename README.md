azure-webapp-maven-plugin needs subscriptionId

Plugin setup:  mvn com.microsoft.azure:azure-webapp-maven-plugin:2.2.0:config
It will write config to pom.xml

F1 is Free Plan

Deploy: mvn package azure-webapp:deploy
It will create App Service Web App and deploy jar file to it


Accessing logs in Azure CLI:
az webapp log config --name <app-name> --resource-group <resource-group-name> --docker-container-logging filesystem
az webapp log tail --name <app-name> --resource-group <resource-group-name>

Logs in browser:
https://jarema-spring-boot-demo.scm.azurewebsites.net/api/logs/docker

SSH in browser:
https://jarema-spring-boot-demo.scm.azurewebsites.net/webssh/host


Clean up:
az group delete --name <resource-group-name> --yes

