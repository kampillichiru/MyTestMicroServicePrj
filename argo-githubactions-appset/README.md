
# Argo CD + GitHub Actions with ApplicationSet (Multi-Env, Multi-Service)

This starter kit demonstrates a **parameterized GitOps setup** using **Argo CD ApplicationSet**.  
It supports multiple microservices (e.g., MicroService-1, MicroService-2, MicroService-3) across multiple environments (DEV, TST, UAT, PROD).

## Structure
```
.
├─ .github/workflows/
│  ├─ update-gitops.yaml               # Reusable workflow (workflow_call)
│  ├─ deploy-microservice-1.yaml       # Example trigger for MS-1
│  ├─ deploy-microservice-2.yaml       # Example trigger for MS-2
│  ├─ deploy-microservice-3.yaml       # Example trigger for MS-3
├─ gitops/
│  ├─ argo-appset.yaml                 # ApplicationSet definition (parametrized)
│  ├─ charts/app-chart/                # Reusable Helm chart
│  └─ microservices/
│     └─ microservice-{1,2,3}/{dev,tst,uat,prod}/values.yaml
```

## How it works
- **ApplicationSet** dynamically generates one Argo CD Application for each service × environment combination (no need for many static files).
- **GitHub Actions** updates the image tag in the right `values.yaml` for a service+env.
- **Argo CD** detects the Git change and deploys.

## Deploy ApplicationSet
```bash
kubectl apply -n argocd -f gitops/argo-appset.yaml
```

This will auto-generate:
- `microservice-1-dev`, `microservice-1-tst`, `microservice-1-uat`, `microservice-1-prod`
- same for microservice-2 and microservice-3

## GitHub Actions
- Use `update-gitops.yaml` workflow as reusable CD workflow.
- Example dispatch in `deploy-microservice-1.yaml`.

---

Happy GitOps-ing!
