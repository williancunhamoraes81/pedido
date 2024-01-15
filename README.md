# Controle de Pedidos

Exemplo de CRUD Sistema de Pedidos
<br/>
## Tabela de Conteúdos 
* pedido 

### 📚 Pré-requisitos

- 🌴 Git
- ☕ Java 1.8
- 📦 Maven
- 🐘 Docker ( arquivo criação na pasta config/devops/docker )
- Executar script de criação de tabela config/devops/db


#### 📋 Clonar repositório

```
git clone https://github.com/williancunhamoraes81/pedido.git
```

#### 🚢 Utilizando API

localhost:8080/api/pedidos { GET }
<br/>
localhost:9800/api/pedidos/filter { POST }
<br/>
localhost:9800/api/pedidos/{id} { GET }
<br/>
localhost:9800/api/pedidos/{id} { DELETE }
<br/>
localhost:8080/api/pedidos { POST }
<br/>

#### ⚙️Compilar a aplicação

```
mvn compile
```


