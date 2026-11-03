# Organizador de Eventos

Aplicación Android nativa, programada en Kotlin, para llevar el control de las tareas y actividades de una persona: reuniones, entregas de proyecto, prácticas, exámenes y otros eventos, con estatus, ubicación, contacto y recordatorio.

Proyecto final de **Desarrollo de Aplicaciones Móviles Nativas** (7.º semestre), Escuela Superior de Cómputo (ESCOM) – IPN.
Autor: José Ricardo Mendoza Castañeda.

## Estado del proyecto

**Primer avance: interfaces.** Ya están todas las pantallas que pide el proyecto, con el menú hamburguesa, la barra de navegación inferior y datos de ejemplo en Inicio, Consultas y Calendario.

Todavía no hay funcionalidad real. No se guarda nada y los botones de guardar, consultar, mapa, contactos, respaldo y restauración no hacen nada todavía (ver [Pendientes](#pendientes)).

| Revisión | Fecha | Alcance |
|---|---|---|
| Primer avance | Miércoles 4 de noviembre | Todas las pantallas, menú hamburguesa y datos de ejemplo |
| Segunda revisión | Miércoles 18 de noviembre | — |
| Entrega final | Miércoles 9 de diciembre | Código fuente, APK y capturas en Teams |

## Requisitos

- Android Studio con soporte para Android Gradle Plugin 9.4.
- Android SDK 37 (compileSdk / targetSdk).
- Un dispositivo o emulador con **Android 10 (API 29)** o superior.
- JDK: Gradle descarga solo el que necesita (toolchain 25), no hace falta instalarlo a mano.

## Compilar y ejecutar

Desde la raíz del proyecto:

```bash
./gradlew assembleDebug     # genera app/build/outputs/apk/debug/app-debug.apk
./gradlew installDebug      # instala en el dispositivo o emulador conectado
./gradlew lint              # análisis estático de Android
./gradlew test              # pruebas unitarias
```

Otra opción es abrir la carpeta en Android Studio y presionar **Run**.

## Navegación

La app usa una sola Activity (`MainActivity`) que cambia de pantalla con fragments y el componente Navigation.

```
┌──────────────────────────────────────┐
│ ☰  Título de la pantalla             │  ← MaterialToolbar
├──────────────────────────────────────┤
│                                      │
│        Pantalla actual (fragment)    │  ← NavHostFragment
│                                      │
├──────────────────────────────────────┤
│   Inicio     Consultar     Salir     │  ← BottomNavigationView
└──────────────────────────────────────┘
```

**Menú hamburguesa** (texto e ícono): Añadir eventos · Consultar y modificar eventos · Mostrar calendario · Realizar respaldo · Restaurar datos · Configuración · Acerca de… · Salir.

**Barra inferior**: Inicio · Consultar · Salir.

"Salir" cierra la aplicación desde cualquiera de los dos menús.

## Pantallas

### Inicio
Pantalla de arranque con los **eventos de hoy y de los próximos 4 días**, en tarjetas con toda su información: categoría, estatus (con color), fecha y hora, contacto, ubicación y descripción.

`fragment_inicio.xml` · `item_evento.xml` · `ui/inicio/InicioFragment.kt`

### 1. Añadir eventos
Formulario para crear un evento:
- **Categoría**: chips de selección única (Reunión, Entrega de proyecto, Práctica, Examen, Otro).
- **Fecha y hora**: campos que abrirán `DatePickerDialog` y `TimePickerDialog`.
- **Descripción**: texto de hasta 200 caracteres.
- **Estatus**: menú desplegable (Pendiente, Realizado, Aplazado).
- **Ubicación**: campo con la dirección y botón *Seleccionar en el mapa*.
- **Contacto**: campo y botón *Elegir de mis contactos* (agenda del celular).
- **Recordatorio**: Al momento, 10 minutos antes, 1 día antes o Sin recordatorio.
- Botón **Guardar evento**.

`fragment_anadir_evento.xml` · `ui/anadir/AnadirEventoFragment.kt`

### 2. Consultar y modificar eventos
- **Filtro por categoría**: menú desplegable que incluye "Todas las categorías".
- **Filtro por fecha** con cuatro modos, y cada modo muestra sus propios campos:
  - *Día*: una fecha (DatePicker).
  - *Rango*: fecha inicial y fecha final.
  - *Mes*: año y selector de mes.
  - *Año*: caja de texto numérica.
- Botones **Consultar** y **Limpiar filtros**, y un buscador dentro de los resultados.
- **Resultados** en tarjetas con categoría, estatus, fecha y hora, contacto, ubicación con botón *Ver mapa* y descripción.
- El ícono de lápiz abre el diálogo **Modificar evento**: cambiar el estatus, el contacto o la ubicación, o eliminar el evento.

`fragment_consultar.xml` · `item_evento_consulta.xml` · `dialog_modificar_evento.xml` · `ui/consultar/ConsultarFragment.kt`

### 3. Mostrar calendario
Calendario mensual. Al elegir un día aparecen debajo sus eventos, y al tocar uno se abre una **hoja inferior** con el detalle completo: fecha, hora, contacto, ubicación, recordatorio y botón *Ver mapa*.

`fragment_calendario.xml` · `bottom_sheet_detalle_evento.xml` · `ui/calendario/CalendarioFragment.kt`

> Se usa el `CalendarView` nativo, que no puede marcar los días con un punto. El proyecto pide ese distintivo, así que el calendario se tendrá que cambiar cuando se agregue la funcionalidad.

### 4. Realizar respaldo
- Elegir la plataforma: **Google Drive** o **Dropbox**.
- Información del último respaldo y de lo que se va a respaldar.
- Botón **Realizar respaldo**.

`fragment_respaldo.xml` · `ui/respaldo/RespaldoFragment.kt`

### 5. Restaurar datos
- Elegir la plataforma.
- Lista de respaldos disponibles con su fecha, número de eventos y tamaño.
- Aviso de que se reemplazarán los eventos actuales.
- Botón **Restaurar datos**.

`fragment_restaurar.xml` · `item_respaldo.xml` · `ui/restaurar/RestaurarFragment.kt`

### 6. Configuración
Administración de los **tipos de evento**: lista de los tipos (por defecto Reunión, Entrega de proyecto, Práctica, Examen y Otro) con botón para eliminar, campo para agregar uno nuevo y opción para restablecer los tipos por defecto.

`fragment_configuracion.xml` · `item_tipo_evento.xml` · `ui/configuracion/ConfiguracionFragment.kt`

### 7. Acerca de
Nombre y versión de la app, descripción y datos del autor, la escuela y la materia.

`fragment_acerca_de.xml` · `ui/acerca/AcercaDeFragment.kt`

### Notificación
No aparece en el menú. Ya está el diseño personalizado de la notificación (`notification_evento.xml`, para `RemoteViews`), con una imagen y todos los datos del evento. Falta programarla.

## Datos de ejemplo

Mientras no haya base de datos, todas las pantallas leen de `model/DatosEjemplo.kt`. Ahí hay 7 eventos de distintas categorías y estatus, más 3 respaldos.

Las fechas se calculan **a partir del día de hoy**: un evento de hace 3 días y los demás entre hoy y dentro de 4 días. Así, Inicio siempre muestra eventos de los próximos 5 días sin importar cuándo se abra la app.

## Estructura del código

```
app/src/main/
├── java/com/richi_mc/proyectodamn/
│   ├── MainActivity.kt          # Drawer, toolbar, barra inferior y navegación
│   ├── model/
│   │   ├── Evento.kt            # Evento + enums Estatus y Recordatorio
│   │   ├── Respaldo.kt
│   │   └── DatosEjemplo.kt      # Datos fijos para las pantallas
│   └── ui/
│       ├── Formato.kt           # Formato de fechas (es-MX) y etiqueta de estatus
│       ├── adapters/            # Adaptadores de RecyclerView
│       ├── inicio/  anadir/  consultar/  calendario/
│       └── respaldo/  restaurar/  configuracion/  acerca/
└── res/
    ├── layout/                  # Pantallas, tarjetas, diálogos y notificación
    ├── menu/                    # menu_drawer.xml y menu_bottom_nav.xml
    ├── navigation/nav_graph.xml # Destinos y títulos de cada pantalla
    ├── drawable/                # Íconos vectoriales Material
    ├── values/                  # Textos, listas, colores, estilos y tema
    └── values-night/            # Colores del modo oscuro
```

Detalles importantes:
- Los **ids de los menús** son iguales a los ids de los destinos de `nav_graph.xml`. Así es como `NavigationUI` sabe a qué pantalla ir. Si se agrega una pantalla, hay que usar el mismo id en los dos lugares.
- Todas las pantallas usan **ViewBinding** y están en **XML** (no Jetpack Compose).
- Todos los textos están en `res/values/strings.xml`. Las listas de categorías, estatus y meses están en `res/values/arrays.xml`.

## Tecnologías

- Kotlin (integrado en Android Gradle Plugin 9.4), Gradle 9.6 con catálogo de versiones (`gradle/libs.versions.toml`).
- Material Components 1.13 (Material 3) con paleta propia y modo oscuro.
- AndroidX Navigation 2.10 (fragments + `NavigationUI`).
- RecyclerView, ViewBinding, `java.time`.

## Pendientes

- Guardar los eventos en una base de datos local (SQLite) o en un servidor (MySQL).
- Conectar `DatePickerDialog` y `TimePickerDialog` en fechas y horas.
- Elegir la ubicación en un mapa (Google Maps u otro) y mostrar la dirección completa.
- Elegir el contacto de la agenda del celular.
- Hacer funcionar las consultas y filtros, y la modificación y eliminación de eventos.
- Calendario que marque con un punto los días que tienen eventos.
- Respaldo y restauración en Google Drive o Dropbox.
- Agregar y eliminar tipos de evento en Configuración.
- Programar las notificaciones según el recordatorio elegido.
