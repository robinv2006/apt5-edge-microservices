# Om op te starten in PS (admin)
# & ".\KubernetesRun.ps1"


# minikube delete
# minikube start
& minikube -p minikube docker-env | Invoke-Expression
docker stop $(docker ps -aq)
docker rm $(docker ps -aq) 



kubectl apply -f deploy-room.yaml
kubectl apply -f deploy-edge.yaml

kubectl get services
kubectl get deployment