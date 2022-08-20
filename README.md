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

#create db server
# sku B_Gen5_1 is basic so there is no vNet integration
az postgres server create -l northeurope -g jaremaspringdemo -n jaremademodbserver -u username -p XXXXXX --sku-name B_Gen5_1

az postgres server create -l northeurope -g jaremaspringdemo -n jaremademodbserver -u username -p XXXXXX --sku-name GP_Gen5_4

az postgres server list-skus -l northeurope

# create vnet rule for db server
az network vnet create --resource-group jaremaspringdemo --name vNet-postres --address-prefixes 10.0.0.0/16 --location northeurope

az network vnet subnet create --resource-group jaremaspringdemo --name subnet-postres --vnet-name vNet-postres --address-prefix 10.0.1.0/24 --service-endpoints Microsoft.SQL

az postgres server vnet-rule create --name rulevNet-postres --resource-group jaremaspringdemo --server jaremademodbserver --vnet-name vNet-postres --subnet subnet-postres


                                        
#create db
az postgres db create -n demodb -g jaremaspringdemo -s jaremademodbserver

#enable connection from my IP address      
az postgres server firewall-rule create --start-ip-address
                                        --end-ip-address
                                        --name
                                        --resource-group
                                        --server-name                                  
az postgres server firewall-rule create --start-ip-address 89.151.34.163 --end-ip-address 89.151.34.163 --name jaremademodbserver_firewall_rule --resource-group jaremaspringdemo --server-name jaremademodbserver                                       
                                        
#create app user (SQL)
create user demouser with encrypted password 'TestHaslo123';
grant all privileges on database demodb to demouser;

#how to open traffic from App Service to Postres DB?


#create key vault
#create service principal
az ad sp create-for-rbac --name jaremaspringdemosp --role Contributor --scopes /subscriptions/mySubscriptionID
will return appId and password

az keyvault create \
    --resource-group jaremaspringdemo \
    --name jaremaspringdemokv \
    --enabled-for-deployment true \
    --enabled-for-disk-encryption true \
    --enabled-for-template-deployment true \
    --location northeurope \
    --query properties.vaultUri \
    --sku standard

az keyvault set-policy --name jaremaspringdemokv --spn APP_ID_OF_SP_ACCOUNT --secret-permissions get list

az keyvault secret set --name "connectionString" \
    --vault-name "jaremaspringdemokv" \
    --value "jdbc:sqlserver://SERVER.database.windows.net:1433;database=DATABASE;"

az keyvault purge --name jaremaspringdemokv
    
    #spring-cloud-azure-starter-keyvault-secrets