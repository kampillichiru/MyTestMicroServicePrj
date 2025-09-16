# Microservices GitOps Repo

## Bootstrap
Apply once to install all services:
```bash
kubectl apply -f argocd/app-of-apps.yaml -n argocd
```

## Add a New Service
1. Create folder:
   ```
   environments/dev/services/backend-payments/
   ```
2. Add `values.yaml` (service config). Example:
   ```yaml
   replicaCount: 1
   image:
     repository: myacr.azurecr.io/backend-payments
     tag: latest
   service:
     type: ClusterIP
     port: 8080
   ingress:
     enabled: true
     host: payments.dev.mycompany.com
   ```
3. Commit & push → ArgoCD auto-deploys.

## CI/CD Flow
- GitHub Actions builds image, pushes to ACR
- Updates values.yaml with new tag
- Commit → ArgoCD syncs → deploys automatically
