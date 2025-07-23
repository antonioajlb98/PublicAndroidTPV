# 🧾 AndroidTPV

**AndroidTPV** es una aplicación Android desarrollada en **Kotlin** que funciona como un TPV (Terminal Punto de Venta) para restaurantes. Permite la gestión de productos, categorías y la impresión de tickets mediante conexión Bluetooth a impresoras compatibles con comandos **ESC/POS**.

---

## 📲 Características principales

- Interfaz moderna usando **Jetpack Compose**
- Arquitectura limpia basada en **MVVM**
- Aplicación modular con responsabilidades separadas
- Base de datos local con **Room**
- Inyección de dependencias con **Koin**
- Conectividad Bluetooth con impresoras térmicas **ESC/POS**
- Gestión intuitiva de productos y categorías
- Impresión de tickets y cálculo de cuenta rápida

---

## 🧭 Navegación de la aplicación

### 🏠 Pantalla Principal
- **Botón TPV**: Accede al módulo de ventas.
- **Botón Gestión de productos**: Accede a la gestión de productos y categorías.
- **Botón Configuración**: Accede a la configuración de la app.

### 🛒 Módulo TPV
- Lista de productos divididos por categorías.
- Carrito editable:
  - Agregar o quitar productos
  - Modificar cantidades
- Funcionalidades:
  - Imprimir ticket
  - Obtener cuenta sin imprimir

### ⚙️ Módulo de Gestión
- Crear, editar y eliminar productos.
- Crear, editar y eliminar categorías.
- Interfaz sencilla y clara.

---

## 🧪 Tecnologías utilizadas

- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose
- **Arquitectura:** MVVM
- **Base de datos:** Room
- **Inyección de dependencias:** Koin
- **Conectividad:** Bluetooth (ESC/POS)
