# Git Application

## Description

This application interacts with the GitHub API to retrieve information about repositories and branches.

## Features

- Retrieve repositories by a GitHub username.
- Get a list of branches for a specific repository.

## Installation

1. Clone the repository: `git clone https://github.com/Taisiia-97/gitApplication.git`
2. Navigate to the project directory: `cd gitApplication`
3. Build the project: `./gradlew build`
4. Run the application: `./gradlew bootRun`

## Usage

To use the application, send GET requests to the following endpoints:

- Retrieve repositories by username:
  - Endpoint: `/api/git/repo/{userName}`
  - Example: `GET /api/git/repo/Taisiia-97`

## Dependencies

- Java 11
- Spring Boot
- WebClient
- Lombok

## Configuration

Make sure to configure the following properties in your application:

- `github.api.url`: The base URL for the GitHub API.
- `github.api.token`: Personal access token for GitHub API authentication.

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
