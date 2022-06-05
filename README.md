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


############################################

PostreSQL

az postgres server create -l northeurope -g jaremaspringdemo -n jaremademodbserver -u username -p XXXXXX --sku-name B_Gen5_1
az postgres server create -l northeurope -g jaremaspringdemo -n jaremademodbserver -u jaremadmin -p XXXXXX --sku-name B_Gen5_1

az postgres server list-skus -l northeurope

az postgres server firewall-rule create --end-ip-address
                                        --name
                                        --resource-group
                                        --server-name
                                        --start-ip-address

az postgres db create -n demodb -g jaremaspringdemo -s jaremademodbserver

#enable connection from my IP address                                        
az postgres server firewall-rule create --end-ip-address 89.151.34.163 --name jaremademodbserver_firewall_rule --resource-group jaremaspringdemo --server-name jaremademodbserver --start-ip-address 89.151.34.163                                        
                                        



