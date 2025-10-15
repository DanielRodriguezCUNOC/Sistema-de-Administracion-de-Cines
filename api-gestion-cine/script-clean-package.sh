#!/bin/bash

# Ruta al directorio del proyecto
PROJECT_DIR="/home/luluwalilith/Documentos/2S 2025 CUNOC/IPC2/proyecto 2/Sistema-de-Administracion-de-Cines/api-gestion-cine"

# Ruta al archivo pom.xml
POM_FILE="$PROJECT_DIR/pom.xml"

# Eliminar el directorio target/ si existe
echo "Eliminando directorio target/..."
rm -rf "$PROJECT_DIR/target/"

# Ejecutar mvn clean
echo "Ejecutando mvn clean..."
mvn clean -f "$POM_FILE"

# Ejecutar mvn compile
echo "Ejecutando mvn compile..."
mvn compile -f "$POM_FILE"

# Ejecutar mvn package
echo "Ejecutando mvn package..."
mvn package -f "$POM_FILE"

echo "Proceso completado."
