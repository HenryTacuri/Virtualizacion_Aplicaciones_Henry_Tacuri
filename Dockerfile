FROM node:latest as dev-deps
WORKDIR /app
COPY AppCrudDocker/package.json AppCrudDocker/package-lock.json ./
RUN npm install

FROM node:latest as builder
WORKDIR /app
COPY --from=dev-deps /app/node_modules ./node_modules
COPY AppCrudDocker .
RUN npm run build

FROM nginx:1.25.5 as prod
EXPOSE 80
COPY --from=builder /app/dist/app-crud-docker /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY AppCrudDocker/nginx/nginx.conf /etc/nginx/conf.d/default.conf