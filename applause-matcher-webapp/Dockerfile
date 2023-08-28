# Build Angular frontend

FROM node:18.17
RUN npm install -g @angular/cli@16.2.0

WORKDIR /webapp
COPY package*.json ./
RUN npm install

COPY . .
RUN ng build
