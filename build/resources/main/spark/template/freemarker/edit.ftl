<!DOCTYPE html>
<html>
<head>
    <title>Registro de Estudiantes</title>
</head>
<body>
<h1>Modificando estudiantes</h1>

<form action="/edit" method="post">
    matricula: <input type="text" name="matricula" value="${matricula}" required/><br>
    nombre: <input type="text" name="nombre" value="${nombre}" required/><br>
    apellido: <input type="text" name="apellido" value="${apellido}" required /><br>
    telefono: <input type="text" name="telefono" value="${telefono}" required /><br>

    <button type="submit" value="Submit">Modificar</button>
</form>

</body>
</html>