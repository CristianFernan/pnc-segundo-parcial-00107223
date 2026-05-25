# Segundo Parcial – Sistema de Gestión de Proyectos y Tareas

## Indicaciones Generales

Lea detenidamente el enunciado y asegúrese de comprender los requisitos funcionales, las reglas de negocio y los entregables antes de comenzar. El ejercicio debe resolverse **individual**, aplicando correctamente la **arquitectura N-Capas** y las buenas prácticas vistas en clase.

> ⚠️ **No se aceptarán commits realizados después de la hora límite establecida para el parcial.**

---

## Sistema de Gestión de Proyectos y Tareas

**Descripción:** Desarrollar una API REST que permita gestionar las tareas dentro de un sistema de proyectos, implementando las operaciones básicas de CRUD, validaciones, reglas de negocio y manejo adecuado de respuestas HTTP.

---

## Entidad `Task`

| Campo | Tipo | Reglas |
|-------|------|--------|
| `id` | Long | Autogenerado |
| `title` | String | Requerido, único por proyecto, no vacío |
| `description` | String | Opcional |
| `status` | Enum | Uno de: `PENDING`, `IN_PROGRESS`, `REVIEW`, `DONE`, `CANCELLED` |
| `priority` | Enum | Uno de: `LOW`, `MEDIUM`, `HIGH`, `CRITICAL` |
| `estimatedHours` | Integer | Obligatorio, ≥ 1 |
| `loggedHours` | Integer | ≥ 0, no puede exceder `estimatedHours` |
| `dueDate` | Date | Obligatorio, debe ser una fecha futura al momento de creación |
| `assignedTo` | String | Requerido, nombre del responsable |
| `active` | Boolean | `true` por defecto; `false` si la tarea es `CANCELLED` o `DONE` |

---

## Reglas de Negocio

- **Título único:** No se permite registrar dos tareas con el mismo título sin importar mayúsculas/minúsculas. No puede existir una tarea sin título, status ni priority.
- **Estado inicial:** Toda tarea nueva debe crearse en estado `PENDING`. No se puede crear una tarea directamente en `DONE` o `CANCELLED`.
- **Horas registradas:** `loggedHours` no puede ser mayor que `estimatedHours`. No se permiten horas negativas.
- **Fecha de vencimiento:** La `dueDate` debe ser una fecha posterior a la de creación; de lo contrario, lanzar excepción.
- **Protección de tareas activas:** No se puede eliminar una tarea con status `IN_PROGRESS` o `REVIEW`.
- **Filtrado:** El listado debe soportar filtros por `status` y `priority`.
    - Ejemplo: `GET /api/tasks?status=IN_PROGRESS&priority=HIGH`

---

## Operaciones CRUD

| Método HTTP | Endpoint | Descripción |
|-------------|----------|-------------|
| `POST` | `/api/tasks` | Crear una nueva tarea |
| `GET` | `/api/tasks` | Listar todas las tareas (con filtros opcionales) |
| `GET` | `/api/tasks/{id}` | Obtener una tarea por ID |
| `PUT` | `/api/tasks/{id}` | Actualizar la información de una tarea |
| `DELETE` | `/api/tasks/{id}` | Eliminar una tarea (respetando reglas de negocio) |

---

## Manejo de Excepciones

Se requieren **al menos 2 excepciones personalizadas**, por ejemplo:

- `ResourceNotFoundException` → HTTP `404`
- `BusinessRuleException` → HTTP `400`

Deben manejarse con un `@RestControllerAdvice` global.

---

## Códigos de Estado HTTP Esperados

| Situación | Código |
|-----------|--------|
| Creación exitosa | `201 Created` |
| Consulta exitosa | `200 OK` |
| Recurso no encontrado | `404 Not Found` |
| Datos inválidos / regla de negocio violada | `400 Bad Request` |
| Eliminación exitosa | `200 OK` o `204 No Content` |

---

## Entregables

Repositorio en **GitHub** con el nombre:

```
pnc-segundo-parcial-<carnet_estudiante>
```

El repositorio debe contener:

1. Código fuente del proyecto Spring Boot.

---

## Rúbrica de Evaluación (90% práctica)

| Criterio                                                    | Porcentaje |
|-------------------------------------------------------------|-----------|
| CRUD completo y funcional                                   | 22% |
| Reglas de negocio implementadas correctamente               | 23% |
| Uso de anotaciones, entities, DTOs y validaciones           | 15% |
| Estructura en capas y claridad del código                   | 10% |
| Manejo de excepciones personalizado (mínimo 2)              | 10% |
| Conexión a base de datos                                    | 5% |
| Buen manejo de códigos de estado HTTP (404, 400, 201, etc.) | 5% |
| **Total**                                                   | **90%** |

> El **10% restante** de la nota del parcial corresponde a la parte teórica, evaluada por separado.