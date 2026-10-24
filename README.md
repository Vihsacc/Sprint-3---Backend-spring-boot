# Alertas Industriais - API Spring Boot

Backend da **Metaindústria** (FIAP, Sprint 4 - entrega final). API REST para registro, consulta e gestão de **alertas industriais**, consumida pelo app Expo.

## Integrante

| Nome | RM |
|------|----|
| Vitor Saccardo | 555611 |

## Vídeo de demonstração (YouTube, não listado)

[COLE AQUI O LINK DO VÍDEO]

## Repositórios

- Backend (este): https://github.com/Vihsacc/Sprint-3---Backend-spring-boot
- Frontend (Expo, README completo com o fluxo de integração): https://github.com/Vihsacc/Sprint-3---Frontend-Expo-integrado

## Pré-requisitos

- Java 17
- Maven 3.8+ (opcional: o projeto inclui o wrapper `mvnw`)

## Como rodar (porta 8080)

```bash
git clone https://github.com/Vihsacc/Sprint-3---Backend-spring-boot.git
cd Sprint-3---Backend-spring-boot

# Windows (PowerShell)
.\mvnw.cmd spring-boot:run

# Mac / Linux
./mvnw spring-boot:run
```

Alternativa: abrir a pasta no IntelliJ e executar a classe `AlertaApiApplication` (não os testes).

Confirme em **http://localhost:8080/alertas**: retorna JSON (`[]` no primeiro uso).

## Banco de dados

H2 em **modo file**: os dados persistem após reiniciar a API (pasta `data/`, ignorada pelo Git).

- Console: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:file:./data/alertadb` · usuário `admin` · senha `admin123`

Se aparecer erro de arquivo bloqueado, há outra instância da API rodando: encerre-a antes.

## Entidade `Alerta`

`id`, `titulo`, `descricao`, `severidade` (`BAIXA`, `MEDIA`, `ALTA`, `CRITICA`), `origem`, `localizacao`, `tipo` (`TEMPERATURA`, `PRESSAO`, `VIBRACAO`, `UMIDADE`, `FALHA_MECANICA`), `status` (`ABERTO` por padrão), `acaoTomada`, `dataAbertura`, `dataResolucao`.

## Endpoints

| Método | Endpoint | Ação | Usado pelo app |
|--------|----------|------|----------------|
| POST | `/alertas` | Criar | Sim |
| GET | `/alertas` | Listar | Sim |
| GET | `/alertas/{id}` | Buscar por ID | Sim |
| PUT | `/alertas/{id}` | Atualizar (`RESOLVIDO` grava `dataResolucao`) | Não |
| DELETE | `/alertas/{id}` | Remover | Não |
| GET | `/alertas/status/{status}` | Filtrar por status | Não |
| GET | `/alertas/severidade/{severidade}` | Filtrar por severidade | Não |
| GET | `/alertas/tipo/{tipo}` | Filtrar por tipo | Não |

Exemplo de `POST /alertas`:

```json
{
  "titulo": "Temperatura crítica detectada",
  "descricao": "Sensor T-01 registrou 98°C, acima do limite de 80°C.",
  "severidade": "CRITICA",
  "origem": "Sensor T-01",
  "localizacao": "Linha de Produção A - Setor 3",
  "tipo": "TEMPERATURA"
}
```

Respostas: `201` criado, `200` sucesso, `204` removido, `400` dados inválidos, `404` não encontrado.

## Arquitetura em camadas

```
src/main/java/com/metaindustria/alertaapi/
├── controller/   AlertaController  (REST + @CrossOrigin)
├── service/      AlertaService
├── repository/   AlertaRepository  (Spring Data JPA)
└── model/        Alerta            (entidade JPA)
```

## Tecnologias

Java 17, Spring Boot 3.2.5, Spring Web, Spring Data JPA, Bean Validation, H2 (file), Maven.
