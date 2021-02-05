<h1>Hello World de JDBC</h1>

<p>JDBC es una API que nos permitirá acceder a bases de datos (BD) desde Java. Con JDBC no es necesario escribir distintos programas para distintas BD, sino que un único programa sirve para acceder a BD de distinta naturaleza. Incluso, podemos acceder a más de una BD de distinta fuente (Oracle, Access, MySql, etc.) en la misma aplicación. Podemos pensar en JDBC como el puente entre una base de datos y nuestro programa Java. Un ejemplo sencillo puede ser un applet que muestra dinámicamente información contenida en una base de datos</p>

<h2>Configurar el proyecto</h2>

<h3>Instalación de drivers</h3>

<p>Un driver es una capa intermedia que traduce las llamadas de JDBC a los APIs específico. El driver para PostGres se obtiene en http://jdbc.postgresql.org</p>

<p>Para instalar el driver lo único que deberemos hacer es incluir el fichero JAR que lo contiene en el CLASSPATH.
</p>

```
export CLASSPATH=$CLASSPATH:/directorio-donde-este/postgresql-42.2.18.jre6.jar
```

<p>Con el driver instalado, podremos cargarlo desde nuestra aplicación simplemente cargando dinámicamente la clase correspondiente al driver:
</p>

```
Class.forName("org.postgresql.Driver");
```
<p>La carga del driver se deberá hacer siempre antes de conectar con la BD. En cuanto a las excepciones, debemos capturar la excepción SQLException en casi todas las operaciones en las que se vea involucrado algún objeto JDBC.</p>


<h2>Establecer conexión con la base de datos</h2>

<p>Una vez cargado el driver debemos establecer la conexión con la BD. Para ello utilizaremos el siguiente método:</p>

```
Connection con = DriverManager.getConnection(url, login, password);
```

<p>La conexión a la BD está encapsulada en un objeto Connection. Para su creación debemos proporcionar la url de la BD y, si la BD está protegida con contraseña, el login y password para acceder a ella. El formato de la url variará según el driver que utilicemos.</p>

<h2>Consulta a la base de datos</h2>

<p>Una vez obtenida la conexión a la BD, podemos utilizarla para realizar consultas, inserción y/o borrado de datos de dicha BD. Todas estas operaciones se realizarán mediante lenguaje SQL. La clase Statement es la que permite realizar todas estas operaciones. La instanciación de esta clase se realiza haciendo uso del siguiente método que proporciona el objeto Connection:</p>

```
Statement stmt = con.createStatement();
```

<p>Código de la clase ConnectionDB (establece conexion con la base de datos)</p>
  
 ```
 package com.example.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	private Connection connection;
	
	private final String driver = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost:5432/devices";
	private final String user = "postgres";
	private final String password = "123";
	
	public Connection connect(){
		try{
			connection = DriverManager.getConnection(url, user, password);
			Class.forName(driver);
		}catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return connection;
	}
	
	public void close() throws SQLException {
		if(connection != null) {
			if (!connection.isClosed()) {
				connection.close();
			}
		}
	}
	
}
 ```

<h2>Sentencias de consulta</h2>
<h3>PreparedStatement</h3>
<p>Una instrucción SQL se precompila y almacena en un objeto PreparedStatement. Este objeto se puede utilizar para ejecutar de forma eficaz esta declaración varias veces.</p>

```
String sql = "INSERT INTO devices(name, id_room, is_indispensable, id_specs) VALUES (?, ?, ?, ?)";
PreparedStatement preparedStatement = connect.prepareStatement(sql);
```

<p>La forma en la que a los signos de interrogacion en el String sql le asignamos valores es a través de métodos setter</p>

```
preparedStatement.setString(1, device.getName());
preparedStatement.setInt(2, device.getIdRoom());
preparedStatement.setBoolean(3, device.getIsIndispensable());
preparedStatement.setInt(4, device.getDeviceSpecs().getId());
preparedStatement.executeUpdate();
```

<h3>ResultSet</h3>
<p>Para obtener datos almacenados en la BD podemos realizar una consulta SQL (query). Podemos ejecutar la consulta utilizando el objeto Statement, pero ahora haciendo uso del método executeQuery al que le pasaremos una cadena con la consulta SQL. Los datos resultantes nos los devolverá como un objeto ResultSet.</p>

```
ResultSet result = stmt.executeQuery(query);
```

<p>La consulta SQL nos devolverá una tabla, que tendrá una serie de campos y un conjunto de registros, cada uno de los cuales consistirá en una tupla de valores correspondientes a los campos de la tabla.</p>

<p>Código de CRUD de la Tabla Device a la BD</p>

```
package com.example.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.domotic.Device;
import com.example.interfaces.CRUD;

public class DataBaseDevice implements CRUD<Device> {

	private ConnectionDB connectionDB = new ConnectionDB();
	private Connection connect;
	private DataBaseSpecs dataBaseSpecs = new DataBaseSpecs();
	
	@Override
	public void register(Device device) throws SQLException {
		try {
			connect = connectionDB.connect();
			dataBaseSpecs.register(device.getDeviceSpecs());
			String sql = "INSERT INTO devices(name, id_room, is_indispensable, id_specs) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, device.getName());
			preparedStatement.setInt(2, device.getIdRoom());
			preparedStatement.setBoolean(3, device.getIsIndispensable());
			preparedStatement.setInt(4, device.getDeviceSpecs().getId());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage()); 
		} finally {
			connect.close();
		}
	}

	@Override
	public void update(Device device) throws SQLException {
		try {
			connect = connectionDB.connect();
			dataBaseSpecs.update(device.getDeviceSpecs());
			String sql = "UPDATE devices set name = ?, id_room = ?, is_indispensable = ? where id = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(4, device.getId());
			preparedStatement.setString(1, device.getName());
			preparedStatement.setInt(2, device.getIdRoom());
			preparedStatement.setBoolean(3, device.getIsIndispensable());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			connect.close();
		}	
	}
	
	@Override
	public void delete(Device device) throws SQLException {
		try {
			connect = connectionDB.connect();
			String sql = "DELETE from devices where id = ?";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setInt(1, device.getId());
			preparedStatement.executeUpdate();
			dataBaseSpecs.delete(device.getDeviceSpecs());
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			connect.close();
		}
	}

	@Override
	public List<Device> read() throws SQLException {
		List<Device> list = null;
		try {
			connect = connectionDB.connect();
			String sql = "SELECT * from devices";
			PreparedStatement preparedStatement = connect.prepareStatement(sql);
			list = new ArrayList<>();
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Device device = new Device();
				device.setId(resultSet.getInt("id"));
				device.setName(resultSet.getString("name"));
				device.setIdRoom(resultSet.getInt("id_room"));
				device.setIsIndispensable(resultSet.getBoolean("is_indispensable"));
				device.setDeviceSpecs(dataBaseSpecs.getSpecificDeviceSpecs(resultSet.getInt("id_specs")));
				list.add(device);
			}
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			connect.close();
		}
		return list;
	}

}
```

