# Vinilos para Android

Vinilos es una aplicación móvil que permite ofrecer a los melómanos, coleccionistas y personas interesadas, grandes volúmenes de música recolectada desde esta misma.

## Iniciemos
### Requerimientos
  - Android Studio | última versión 
  - JDK >= 16.0
  - Gradle >= 7

### Instalación
  1. `git clone` al repositorio en su máquina local.
  2.  Use git para verificar que esté en la rama `main`
  3.  Abrir el proyecto en Android Studio, sincronizar Gradle y ejecutar.
  
## Ejecución de pruebas con Espresso

1. Asegurarse que el proyecto esté sincronizado con el Gradle.
2. Ir a la ruta: `src/androidTest/java/com/example/vinilos/ui/activities/`
3. Sobre el archivo `TestHU01.java` dar clic derecho.
4. Dar clic en run.

###### Nota: Verificar que el proyecto esté corriendo correctamente antes de inicar la pruebas. Evitar mensajes como: "System UI isn't responding" en la la pantalla.

### APK para instalar en dispositivo físico

El APK se encuentra en el directorio `apk-output` del raíz.

Una vez copiado este archivo en el dispositivo, su proceso de instalación es ampliamente conocido.
