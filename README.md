\# 🌱 AgroCalendario



\## 📱 Descripción



\*\*AgroCalendario\*\* es una aplicación móvil desarrollada para apoyar a los agricultores en la organización y seguimiento de sus actividades agrícolas.



La aplicación permite registrar, consultar, editar y eliminar actividades, programar recordatorios y consultar información climática para facilitar la planificación de las labores agrícolas.



\## 🎯 Objetivo



Brindar una herramienta móvil sencilla que permita a los agricultores organizar sus actividades agrícolas y consultar información útil para planificar sus labores de campo.



\## 👨‍🌾 Público objetivo



La aplicación está dirigida principalmente a agricultores que necesitan llevar un registro organizado de sus actividades y recibir recordatorios sobre las labores programadas.



\## ✨ Funcionalidades



\* 👤 Registro e inicio de sesión de usuarios.

\* 📅 Calendario para consultar actividades agrícolas.

\* 🌱 Registro de actividades agrícolas.

\* ✏️ Edición de actividades.

\* 🗑️ Eliminación de actividades.

\* ✅ Marcado de actividades como realizadas.

\* 🔔 Programación de recordatorios.

\* 🔁 Configuración de repetición de actividades.

\* 🌦️ Consulta de información climática.

\* 💾 Almacenamiento de preferencias mediante DataStore.

\* 🌙 Modo claro, oscuro y automático según el dispositivo.

\* 🔥 Almacenamiento de información mediante Firebase Firestore.



\## 🌦️ Información climática



AgroCalendario incorpora una funcionalidad de consulta del clima que permite visualizar información útil para las actividades agrícolas, como:



\* 🌡️ Temperatura.

\* 💧 Humedad.

\* 🌧️ Precipitación.

\* 💨 Velocidad del viento.



Esta información puede ayudar al agricultor a considerar las condiciones climáticas al momento de planificar sus actividades.



\## 🔔 Notificaciones



La aplicación permite configurar recordatorios para las actividades agrícolas.



El usuario puede activar o desactivar los recordatorios y establecer opciones relacionadas con la programación de avisos.



Las notificaciones permiten recordar al agricultor las actividades que tiene programadas.



\## 💾 DataStore



La aplicación utiliza \*\*DataStore Preferences\*\* para almacenar preferencias locales del usuario, entre ellas:



\* Estado de los recordatorios.

\* Hora del recordatorio.

\* Minutos del recordatorio.

\* Días de anticipación.

\* Preferencia de tema de la aplicación.



Esto permite conservar las configuraciones del usuario entre las sesiones de uso de la aplicación.



\## 🔥 Firebase y Firestore



AgroCalendario utiliza Firebase para la autenticación de usuarios y \*\*Cloud Firestore\*\* para almacenar las actividades agrícolas asociadas a cada usuario.



La información de las actividades se organiza de manera que cada usuario pueda acceder a sus propios registros.



\## 🌙 Temas de la aplicación



AgroCalendario dispone de tres opciones de apariencia:



\* ☀️ \*\*Modo claro\*\*

\* 🌙 \*\*Modo oscuro\*\*

\* 📱 \*\*Usar tema del dispositivo\*\*



La preferencia seleccionada se almacena mediante DataStore.



\## 🏗️ Arquitectura



La aplicación utiliza el patrón arquitectónico \*\*MVVM (Model-View-ViewModel)\*\* para organizar el código y separar las responsabilidades de cada componente.



\### Estructura general



```text

UI / Screens

\&#x20;    ↓

ViewModel

\&#x20;    ↓

Repository

\&#x20;    ↓

Firebase / Firestore

\&#x20;    ↓

DataStore / Servicios externos

```



\### Componentes principales



\*\*Model:\*\* representa los datos utilizados por la aplicación.



\*\*View:\*\* contiene las pantallas desarrolladas con Jetpack Compose.



\*\*ViewModel:\*\* administra el estado de la interfaz y coordina las operaciones de la aplicación.



\*\*Repository:\*\* se encarga de gestionar el acceso a los datos.



\## 🛠️ Tecnologías utilizadas



\* \*\*Kotlin\*\*

\* \*\*Android Studio\*\*

\* \*\*Jetpack Compose\*\*

\* \*\*Material 3\*\*

\* \*\*MVVM\*\*

\* \*\*Firebase Authentication\*\*

\* \*\*Cloud Firestore\*\*

\* \*\*DataStore Preferences\*\*

\* \*\*Kotlin Coroutines\*\*

\* \*\*StateFlow\*\*

\* \*\*WorkManager\*\*

\* \*\*API de clima\*\*



\## 📱 Pantallas principales



La aplicación cuenta con diferentes pantallas para facilitar la navegación:



\* 🏠 Pantalla principal.

\* 🔐 Inicio de sesión.

\* 📝 Registro de usuario.

\* 📅 Calendario.

\* 🌱 Mis actividades.

\* 🌦️ Clima agrícola.

\* ⚙️ Configuración.

\* 👤 Perfil.



\## ⚙️ Requisitos



Para ejecutar el proyecto se necesita:



\* Android Studio.

\* JDK compatible con el proyecto.

\* Android SDK.

\* Dispositivo Android o emulador.

\* Conexión a Internet para las funcionalidades que requieren servicios externos.

\* Configuración correspondiente de Firebase.



\## 🚀 Instalación



1\. Clonar el repositorio:



```bash

git clone https://github.com/listefuchatorres-cmyk/AgroCalendario.git

```



2\. Abrir el proyecto desde \*\*Android Studio\*\*.



3\. Esperar a que Gradle sincronice las dependencias.



4\. Configurar Firebase para el proyecto si es necesario.



5\. Ejecutar la aplicación en un dispositivo Android o emulador.



\## 📂 Estructura general del proyecto



```text

AgroCalendario/

├── app/

│   └── src/

│       └── main/

│           └── java/

│               └── com/example/agrocalendario/

│                   ├── data/

│                   ├── navigation/

│                   ├── notification/

│                   ├── screens/

│                   ├── ui/

│                   └── viewmodel/

├── gradle/

├── build.gradle.kts

├── settings.gradle.kts

└── README.md

```



\## 👩‍💻 Autora



\*\*Estefani Torres\*\*



Tecnología Universitaria en Desarrollo de Software.



Proyecto académico: \*\*AgroCalendario\*\* 🌱



\---



\## 📌 Estado del proyecto



\*\*AgroCalendario\*\* se encuentra en desarrollo como proyecto académico de aplicación móvil orientada al apoyo de las actividades agrícolas.



&#x20;


ANEXOS
1. Pantalla bienvenida Y Inicio de sesión y registro   
<img width="410" height="437" alt="image" src="https://github.com/user-attachments/assets/6a6492e6-5155-4331-82bb-229242b83bd4" />
2. Pantalla Iniciar cesión Continuar con Google
<img width="376" height="438" alt="image" src="https://github.com/user-attachments/assets/280ed215-9a97-4dd3-9832-b76f1c223955" />
3. Pantalla Continuar con Google
<img width="405" height="385" alt="image" src="https://github.com/user-attachments/assets/a217d7c5-c348-43c8-9842-204360ea976d" />
4. Pantalla Crear Cuenta
<img width="382" height="374" alt="image" src="https://github.com/user-attachments/assets/02442f14-3c42-472c-ae24-81d0bd8c10b8" />
5. Pantalla Principal Calendario
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/73fe2197-5e22-4043-b678-02009b3925e0" />
6. Pantalla Calendario
<img width="380" height="638" alt="image" src="https://github.com/user-attachments/assets/7dd2dfa9-66aa-4bd9-96b3-cf5ca6dd91d5" />
7. Pantalla Registrar Actividad
<img width="408" height="578" alt="image" src="https://github.com/user-attachments/assets/f89bd0ee-c6a8-408b-8b01-30f2da4bdada" />
8. Pantalla Mis Actividades
<img width="394" height="473" alt="image" src="https://github.com/user-attachments/assets/0a765eb9-b895-4035-98a1-0bd0c9e48ee1" />
9. Pantalla Perfil
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/fe2a80c6-f709-4329-9c7b-73c4b77d7961" />
10. Pantalla Configuracion y Apariencia modo oscuro modo, claro
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/fb5d77d8-b16c-45e7-b04f-8c5419d8d759" />

<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/14e70137-6977-4b4c-bfa9-1e317635a90c" />


                           

 




