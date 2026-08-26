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



