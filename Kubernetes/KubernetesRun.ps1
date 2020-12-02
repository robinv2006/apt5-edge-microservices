# Om op te starten in PS (admin)
# & ".\KubernetesRun.ps1"

# minikube delete
# minikube start

kubectl delete deployment --all
kubectl delete service --all

kubectl apply -f deploy-room.yaml
kubectl apply -f deploy-customer.yaml
kubectl apply -f deploy-employee.yaml
kubectl apply -f deploy-hotel.yaml
kubectl apply -f deploy-edge.yaml

kubectl get services
kubectl get deployment