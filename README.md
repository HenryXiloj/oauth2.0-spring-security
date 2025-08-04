# Microservices Security Patterns and Protocols with Spring Security

A comprehensive guide to implementing security patterns in microservices architecture using Spring Boot, OAuth2, JWT, OpenID Connect, and SAML 2.0.

📘 Blog Post: [Microservices Security Patterns and Protocols with Spring Security](https://jarmx.blogspot.com/2022/10/microservices-security-patterns-and.html)

## 📋 Table of Contents

- [Overview](#overview)
- [Security Protocols Covered](#security-protocols-covered)
- [Prerequisites](#prerequisites)
- [OAuth 2.0 Authorization Framework](#oauth-20-authorization-framework)
- [JSON Web Tokens (JWT)](#json-web-tokens-jwt)
- [OpenID Connect](#openid-connect)
- [SAML 2.0](#saml-20)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Configuration Examples](#configuration-examples)
- [Testing](#testing)
- [Source Code](#source-code)
- [References](#references)

## 🔍 Overview

This repository demonstrates the implementation of various security patterns and protocols commonly used in microservices architectures:

- **OAuth 2.0 Authorization Framework** with Spring Boot 2.5.2 and Java 11
- **JSON Web Tokens (JWT)** with Spring Boot 2.4.4 and Kotlin 1.7.10
- **OpenID Connect** implementation
- **SAML V2.0** integration

## 🛡️ Security Protocols Covered

### 1. OAuth 2.0 Authorization Framework
Industry-standard protocol for authorization focusing on client developer simplicity while providing specific authorization flows for various application types.

### 2. JSON Web Tokens (JWT)
Compact, URL-safe means of representing claims to be transferred between two parties with digital signature verification.

### 3. OpenID Connect
Simple identity layer on top of OAuth 2.0 protocol for identity verification and basic profile information retrieval.

### 4. SAML 2.0
XML-based standard for exchanging authentication and authorization data between security domains.

## 📋 Prerequisites

- Java 11 or higher
- Maven 3.6+
- Spring Boot 2.4.4+ / 2.5.2+
- IntelliJ IDEA (recommended)
- Basic understanding of OAuth 2.0, JWT, and Spring Security

## 🔐 OAuth 2.0 Authorization Framework

### OAuth 2.0 Roles

- **Resource Owner**: User or system that owns the data to be shared
- **Resource Server**: Server protecting user resources (e.g., Facebook, Google)
- **Client Application**: Application requiring access to protected resources
- **Authorization Server**: Server authorizing client app to access resources

### Key Components

#### Authorization Server Configuration
```yaml
server:
  port: 8081
  servlet:
    context-path: /auth

spring:
  ldap:
    embedded:
      base-dn: dc=springframework,dc=org
      ldif: classpath:test-server.ldif
      port: 8389
      url: ldap://localhost:8389/

client:
  id: henry
  secret: secret
  redirect: http://localhost:8082/ui/index
```

#### Client Application Configuration
```yaml
server:
  port: 8082
  servlet:
    context-path: /ui

spring:
  security:
    oauth2:
      client:
        registration:
          auth:
            client-id: henry
            client-secret: secret
            client-authentication-method: basic
            scope: user_info
            authorization-grant-type: authorization_code
            redirect-uri: http://localhost:8082/ui/index
        provider:
          auth:
            authorization-uri: http://localhost:8081/auth/oauth/authorize
            token-uri: http://localhost:8081/auth/oauth/token
            user-name-attribute: preferred_username
```

## 🎫 JSON Web Tokens (JWT)

### JWT Structure
JWT consists of three parts separated by dots (.):
- **Header**: Token type and signing algorithm
- **Payload**: Claims containing user data
- **Signature**: Verification of token integrity

### Implementation with Kotlin
The JWT implementation uses Spring Boot 2.4.4 with Kotlin 1.7.10, providing:
- Token-based authentication
- Stateless session management
- LDAP integration
- CORS configuration

### Key Features
- Access token validity: 3600 seconds
- Refresh token support
- JWT signing key configuration
- LDAP authentication integration

## 🆔 OpenID Connect

OpenID Connect extends OAuth 2.0 by adding an identity layer that allows clients to:
- Verify end-user identity
- Obtain basic profile information
- Support various client types (web, mobile, JavaScript)
- Integrate encryption and discovery features

### Benefits
- Standardized identity verification
- REST-like profile information access
- Extensible specification suite
- Built-in OAuth 2.0 capabilities

## 📄 SAML 2.0

Security Assertion Markup Language (SAML) 2.0 is an XML-based standard for transferring identity data between:
- **Identity Provider (IdP)**: Authentication authority
- **Service Provider (SP)**: Application requesting authentication

### Key Characteristics
- XML-based assertions
- Single Sign-On (SSO) support
- Cross-domain authentication
- Enterprise-grade security

## 📁 Project Structure

```
microservices-security/
├── oauth2-authorization-server/
│   ├── src/main/java/com/henry/
│   │   ├── configuration/
│   │   │   ├── AuthServerConfig.java
│   │   │   └── SecurityConfig.java
│   │   └── controller/
│   │       └── HelloResource.java
│   ├── src/main/resources/
│   │   ├── application.yml
│   │   └── test-server.ldif
│   └── pom.xml
├── oauth2-client/
│   ├── src/main/java/com/henry/
│   │   ├── configuration/
│   │   │   └── UiSecurityConfig.java
│   │   └── controller/
│   │       └── IndexController.java
│   ├── src/main/resources/
│   │   └── application.yml
│   └── pom.xml
└── jwt-kotlin-implementation/
    ├── src/main/kotlin/com/hxiloj/
    │   ├── configuration/
    │   │   ├── AuthorizationServerConfig.kt
    │   │   ├── ResourceServerConfig.kt
    │   │   └── SpringSecurityConfig.kt
    │   └── controller/
    │       └── DefaultRestController.kt
    ├── src/main/resources/
    │   ├── application.yml
    │   └── test-server.ldif
    └── pom.xml
```

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd microservices-security
```

### 2. OAuth 2.0 Setup

#### Start Authorization Server
```bash
cd oauth2-authorization-server
mvn spring-boot:run
```
Server runs on: `http://localhost:8081/auth`

#### Start Client Application
```bash
cd oauth2-client
mvn spring-boot:run
```
Client runs on: `http://localhost:8082/ui`

### 3. JWT Implementation Setup
```bash
cd jwt-kotlin-implementation
mvn spring-boot:run
```
JWT server runs on: `http://localhost:9000/auth`

### 4. LDAP Test Credentials
- **Username**: henry
- **Password**: 123

## ⚙️ Configuration Examples

### Maven Dependencies (OAuth2)
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.security.oauth</groupId>
        <artifactId>spring-security-oauth2</artifactId>
        <version>2.3.3.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-ldap</artifactId>
    </dependency>
    <!-- Additional dependencies... -->
</dependencies>
```

### Maven Dependencies (JWT + Kotlin)
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.security.oauth</groupId>
        <artifactId>spring-security-oauth2</artifactId>
        <version>2.3.5.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-jwt</artifactId>
        <version>1.0.10.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-stdlib-jdk8</artifactId>
    </dependency>
    <!-- Additional dependencies... -->
</dependencies>
```

## 🧪 Testing

### OAuth 2.0 Flow Testing

1. **Access Client Application**
   ```
   GET http://localhost:8082/ui
   ```

2. **Authorization Request** (automatically generated)
   ```
   https://YOUR_DOMAIN/authorize?
   response_type=code&
   client_id=YOUR_CLIENT_ID&
   redirect_uri=https://YOUR_APP/callback&
   scope=SCOPE&
   state=STATE
   ```

3. **Generate Access Token**
   ```bash
   curl --request POST \
     --url 'http://localhost:8081/auth/oauth/token' \
     --header 'content-type: application/x-www-form-urlencoded' \
     --data grant_type=authorization_code \
     --data 'client_id=henry' \
     --data client_secret=secret \
     --data code=YOUR_AUTHORIZATION_CODE \
     --data 'redirect_uri=http://localhost:8082/ui/index'
   ```

4. **Access Protected Resource**
   ```
   GET http://localhost:8081/auth/rest/hello?access_token=your_token
   ```

### JWT Testing

1. **Get JWT Access Token**
   ```
   POST http://localhost:9000/oauth/token
   ```

2. **Access Protected Endpoint**
   ```
   GET http://localhost:9000/principal
   Authorization: Bearer YOUR_JWT_TOKEN
   ```

## 📚 Source Code

- **OAuth 2.0 Authorization Framework**: [GitHub Repository](https://github.com/HenryXiloj/oauth2.0-spring-security)
- **JSON Web Tokens**: [GitHub Repository](https://github.com/HenryXiloj/jwt-oauth2.0-spring-security)
- **SAML 2.0**: [GitHub Repository](https://github.com/HenryXiloj/spring-boot-security-saml2.0-keycloak)

## 🔧 Key Features

- ✅ OAuth 2.0 Authorization Code Flow
- ✅ JWT Token Generation and Validation
- ✅ LDAP Authentication Integration
- ✅ CORS Configuration
- ✅ Resource Server Protection
- ✅ Client Application OAuth2 Integration
- ✅ Kotlin Support for JWT Implementation
- ✅ Spring Security Configuration
- ✅ RESTful API Protection

## 📖 References

- [OAuth 2.0 Specification](https://oauth.net/2/)
- [JWT Introduction](https://jwt.io/introduction)
- [OpenID Connect](https://openid.net/connect/)
- [SAML Specifications](http://saml.xml.org/saml-specifications)
- [Spring Security OAuth](https://spring.io/projects/spring-security-oauth)
- [Spring LDAP Guide](https://spring.io/guides/gs/authenticating-ldap/)


---

**Note**: The implementations shown use specific versions of Spring Boot and may require updates for newer versions. Always refer to the official Spring Security documentation for the latest security best practices.
