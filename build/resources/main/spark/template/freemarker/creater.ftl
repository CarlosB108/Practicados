<!DOCTYPE html>
<html>
<head>
    <title>Creando estudiantes</title>
</head>
<body>
<h1>Modificando estudiantes</h1>

<form action="/create" method="post">
    matricula: <input type="text" name="matricula" required/><br>
    nombre: <input type="text" name="nombre" required/><br>
    apellido: <input type="text" name="apellido" required /><br>
    telefono: <input type="text" name="telefono" required /><br>

    <button type="submit" value="Submit">Crear</button>
</form>

</body>
</html>