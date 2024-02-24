# Git Application

## Description

This application interacts with the GitHub API to retrieve information about repositories and branches.

## Features

- Retrieve repositories by a GitHub username and a list of branches for a specific repository.

## Installation

1. Clone the repository: `git clone https://github.com/Taisiia-97/gitApplication.git`
2. Navigate to the project directory: `cd gitApplication`
3. Open the `application.yml` file located in the `src/main/resources` directory.
4. Set your GitHub Personal Access Token (PAT) in the `github.api.token` property.
5. Save and close the `application.yml` file.
6. Build the project.
7. Run the application.

## Usage

To use the application, send GET requests to the following endpoints:

- Retrieve repositories by username:
  - Endpoint: `/api/git/repo/{userName}`
  - Example: `GET /api/git/repo/Taisiia-97`

## Dependencies

- Java 21
- Spring Boot 3
- WebClient
- Lombok

## Configuration

Ensure that you have configured the `application.yml` file with the following properties under the `github.api` section:

```yaml
server:
  port: 8090
github:
  api:
    url: "https://api.github.com"
    token: "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"
.





## Endpoints

### Retrieve Repositories by Username

- **URL:** `/api/git/repo/{userName}`
- **Method:** `GET`
- **URL Params:**
  - `userName`: The GitHub username for which to retrieve repositories.
- **Response:**
  ```json
  [
    {
      "name": "repository_name",
      "owner": "repository_owner",
      "branches": [
        {
          "name": "branch_name",
          "lastCommitSha": "last_commit_sha"
        },
        ...
      ]
    },
    ...
  ]
