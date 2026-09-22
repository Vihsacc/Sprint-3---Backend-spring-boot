# alerta-api — Backend Spring Boot

API REST para gestão de alertas industriais (Metaindústria).

## Como rodar

```bash
# Mac/Linux
./mvnw spring-boot:run

# Windows PowerShell
.\mvnw.cmd spring-boot:run

# Ou pelo IntelliJ: botão ▶️ na classe AlertaApiApplication
```

Confirme em: http://localhost:8080/alertas → deve retornar `[]`

Console H2: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:file:./data/alertadb`
- Usuário: `admin` | Senha: `admin123`

## Endpoints

| Método | Endpoint                          | Ação                  |
|--------|-----------------------------------|-----------------------|
| POST   | `/alertas`                        | Criar alerta          |
| GET    | `/alertas`                        | Listar todos          |
| GET    | `/alertas/{id}`                   | Buscar por ID         |
| PUT    | `/alertas/{id}`                   | Atualizar             |
| DELETE | `/alertas/{id}`                   | Remover               |
| GET    | `/alertas/status/{status}`        | Filtrar por status    |
| GET    | `/alertas/severidade/{severidade}`| Filtrar por severidade|
| GET    | `/alertas/tipo/{tipo}`            | Filtrar por tipo      |

## Arquitetura

```
model/      → Alerta.java
repository/ → AlertaRepository.java
service/    → AlertaService.java
controller/ → AlertaController.java  (@CrossOrigin incluso)
```
