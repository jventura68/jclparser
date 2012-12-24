#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
import MySQLdb
import sqlite3
import sys


# Claves de las rutas en la tabla de rutas tal como se asumen en la aplicación
RUTA_MODERNISTA = 1
RUTA_PICASSO = 2
LISTA_HOTELES = 3
RUTA_HISTORICA = 4
LISTA_RESTAURANTES = 5
RUTA_MUSEOS = 6

MUSEO = 1
MONUMENTO = 3
HOTEL = 100
RESTAURANTE = 101


# Conectamos con mysql 
mySqlDb=MySQLdb.connect(host='localhost',user='root',passwd='alamierda',db='coruna',charset = "utf8", use_unicode = True)

# Recuperamos los datos de la tabla de edificios y los insertamos en la de pois de sqlite
cursorMysql = mySqlDb.cursor()
sql='SELECT * from edificios;'
cursorMysql.execute(sql)
resultado=cursorMysql.fetchall()

#-----------------------------------------------------------------------------------------
# Conectamos con sqlite
#-----------------------------------------------------------------------------------------
sqliteDb = sqlite3.connect("./rutasCoruna.sqlite")
cursorSqlite = sqliteDb.cursor()

#-----------------------------------------------------------------------------------------
# Borramos todas las tablas de la bd
#-----------------------------------------------------------------------------------------
print "Borrando pois..."

try:
    cursorSqlite.execute('delete from poi')
except sqliteDb.Error, e:
        print "Error borrando tabla de pois: %s" % e.args[0]
        sys.exit(1)
    

print "Borrando rutas..."

try:
    cursorSqlite.execute('delete from ruta')
except sqliteDb.Error, e:
        print "Error borrando tabla de rutas: %s" % e.args[0]
        sys.exit(1)


print "Borrando relaciones entre pois y rutas..."

try:
    cursorSqlite.execute('delete from poiRuta')
except sqliteDb.Error, e:
        print "Error borrando tabla de pois/rutas: %s" % e.args[0]
        sys.exit(1)


print "Borrando restaurantes..."

try:
    cursorSqlite.execute('delete from restaurante')
except sqliteDb.Error, e:
        print "Error borrando tabla de restaurantes: %s" % e.args[0]
        sys.exit(1)


print "Borrando hoteles..."

try:
    cursorSqlite.execute('delete from hotel')
except sqliteDb.Error, e:
        print "Error borrando tabla de hoteles: %s" % e.args[0]
        sys.exit(1)

sqliteDb.commit()
    
#-----------------------------------------------------------------------------------------
# Cargamos lo que hay en la tabla "edificios" de mySql en la tabla "pois" de sqlite
#
#
#-----------------------------------------------------------------------------------------
# Cargamos los datos de la tabla "edificios" de mySqle en lista
print "\nCargando datos de edificios..."
filas = []
clavesPois = []
idPoi = 0

for registro in resultado:
    columnas = []  

    columnas.append(registro[8]) 
    columnas.append(idPoi)
    columnas.append(str(registro[6]))
    columnas.append(str(registro[7]))
    columnas.append(registro[1])
    columnas.append(' ')
    columnas.append(MONUMENTO)
    columnas.append(registro[9])
    columnas.append(registro[2])
    columnas.append(0)
    columnas.append(' ')
    
    filas.append(columnas)
    clavesPois.append(idPoi);   # nos guardamos las claves de los poi para asociarlos a la ruta
    
    idPoi = idPoi + 1


for fila in filas:
    try:
        cursorSqlite.execute('insert into poi (descPoi, _id, latitud, longitud, nombrePoi, url, categoria, \
            uriImagen, direccion, comercial, telefono) values (?,?,?,?,?,?,?,?,?,?,?)', fila)
    
    except sqliteDb.Error, e:
        print "Error cargando pois edificios modernistas: %s" % e.args[0]
        sys.exit(1)

sqliteDb.commit()
print "Cargados pois de edificios modernistas"

#-----------------------------------------------------------------------------------------
# Damos de alta la ruta en la tabla de rutas        
#-----------------------------------------------------------------------------------------
print "\nCreando ruta modernista..."

filas = []
filas.append([u'Un recorrido por los edificios emblemáticos de finales del siglo XIX', RUTA_MODERNISTA, u'Edificios emblemáticos', u'T', \
    16, 43.367893, -8.401544])
        
for fila in filas:
    try:
        cursorSqlite.execute('insert into ruta (descRuta, _id, nombreRuta, tipo, default_zoom_level, \
        default_latitude, default_longitude) values (?,?,?,?,?,?,?)', fila)
   
    except sqliteDb.Error, e:
        print "Error creando ruta modernista: %s " % e.args[0]
        sys.exit(1)

print "Creada ruta modernista"

sqliteDb.commit()


#-----------------------------------------------------------------------------------------
# Damos de alta las relaciones poi/ruta         
#-----------------------------------------------------------------------------------------
print "\nAsociando pois a ruta..."

for clave in clavesPois:
    try:
        fila = (clave, RUTA_MODERNISTA)
        cursorSqlite.execute('insert into poiRuta (idPoi, idRuta) values (?, ?)', fila)

    except sqliteDb.Error, e:
        print "Error asociando pois a ruta modernista: %s " % e.args[0]
        sys.exit(1)

print "Creadas relaciones de la ruta modernista"

sqliteDb.commit()






#-----------------------------------------------------------------------------------------
# Cargamos lo que hay en la tabla "historica" de mySql en la tabla "pois" de sqlite
#
#
#-----------------------------------------------------------------------------------------
print "\nCargando datos de la ruta histórica..."
filas = []
clavesPois = []

sql='SELECT * from historica;'
cursorMysql.execute(sql)
resultado=cursorMysql.fetchall()

for registro in resultado:
    columnas = []  

    columnas.append(registro[3]) 
    columnas.append(idPoi)
    columnas.append(str(registro[5]))
    columnas.append(str(registro[6]))
    columnas.append(registro[1])
    columnas.append(' ')
    columnas.append(MONUMENTO)
    columnas.append(registro[4])
    columnas.append(registro[2])
    columnas.append(0)
    columnas.append(' ')
    
    filas.append(columnas)
    clavesPois.append(idPoi);   # nos guardamos las claves de los poi para asociarlos a la ruta
    
    idPoi = idPoi + 1


for fila in filas:
    try:
        cursorSqlite.execute('insert into poi (descPoi, _id, latitud, longitud, nombrePoi, url, categoria, \
            uriImagen, direccion, comercial, telefono) values (?,?,?,?,?,?,?,?,?,?,?)', fila)
    
    except sqliteDb.Error, e:
        print "Error cargando pois ruta histórica: %s" % e.args[0]
        sys.exit(1)

sqliteDb.commit()
print "Cargados pois de la ruta histórica"


#-----------------------------------------------------------------------------------------
# Damos de alta la ruta en la tabla de rutas        
#-----------------------------------------------------------------------------------------
print "\nCreando ruta histórica..."

filas = []
filas.append([u'Descubra los principales edificios religiosos y militares de la parte antigua de la ciudad', RUTA_HISTORICA, u'A Coruña histórica', u'T', \
    16, 43.369041, -8.392680])
        
for fila in filas:
    try:
        cursorSqlite.execute('insert into ruta (descRuta, _id, nombreRuta, tipo, default_zoom_level, \
        default_latitude, default_longitude) values (?,?,?,?,?,?,?)', fila)
   
    except sqliteDb.Error, e:
        print "Error creando ruta histórica: %s " % e.args[0]
        sys.exit(1)

print "Creada ruta histórica"

sqliteDb.commit()


#-----------------------------------------------------------------------------------------
# Damos de alta las relaciones poi/ruta         
#-----------------------------------------------------------------------------------------
print "\nAsociando pois a ruta..."

for clave in clavesPois:
    try:
        fila = (clave, RUTA_HISTORICA)
        cursorSqlite.execute('insert into poiRuta (idPoi, idRuta) values (?, ?)', fila)

    except sqliteDb.Error, e:
        print "Error asociando pois a ruta histórica: %s " % e.args[0]
        sys.exit(1)

print "Creadas relaciones de la ruta histórica"

sqliteDb.commit()






#-----------------------------------------------------------------------------------------
# Cargamos los museos
#
#
#-----------------------------------------------------------------------------------------
print "\nCargando museos..."
filas = []
clavesPois = []

sql='SELECT * from museos;'
cursorMysql.execute(sql)
resultado=cursorMysql.fetchall()

for registro in resultado:
    columnas = []  

    columnas.append(registro[4]) 
    columnas.append(idPoi)
    columnas.append(str(registro[8]))
    columnas.append(str(registro[9]))
    columnas.append(registro[1])
    columnas.append(' ')
    columnas.append(MUSEO)
    columnas.append(registro[6])
    columnas.append(registro[2])
    columnas.append(0)
    columnas.append(registro[3])
    
    filas.append(columnas)
    clavesPois.append(idPoi);   # nos guardamos las claves de los poi para asociarlos a la ruta
    
    idPoi = idPoi + 1


for fila in filas:
    try:
        cursorSqlite.execute('insert into poi (descPoi, _id, latitud, longitud, nombrePoi, url, categoria, \
            uriImagen, direccion, comercial, telefono) values (?,?,?,?,?,?,?,?,?,?,?)', fila)
    
    except sqliteDb.Error, e:
        print "Error cargando pois museos: %s" % e.args[0]
        sys.exit(1)

sqliteDb.commit()
print "Cargados pois de museos"


#-----------------------------------------------------------------------------------------
# Damos de alta la ruta en la tabla de rutas        
#-----------------------------------------------------------------------------------------
print "\nCreando ruta de museos..."

filas = []
filas.append([u'Desde la Domus hasta la vida del joven Picasso', RUTA_MUSEOS, u'Museos', u'T', \
    14, 43.353413, -8.403215])
        
for fila in filas:
    try:
        cursorSqlite.execute('insert into ruta (descRuta, _id, nombreRuta, tipo, default_zoom_level, \
        default_latitude, default_longitude) values (?,?,?,?,?,?,?)', fila)
   
    except sqliteDb.Error, e:
        print "Error creando ruta de museos: %s " % e.args[0]
        sys.exit(1)

print "Creada ruta de museos"

sqliteDb.commit()


#-----------------------------------------------------------------------------------------
# Damos de alta las relaciones poi/ruta         
#-----------------------------------------------------------------------------------------
print "\nAsociando pois a ruta..."

for clave in clavesPois:
    try:
        fila = (clave, RUTA_MUSEOS)
        cursorSqlite.execute('insert into poiRuta (idPoi, idRuta) values (?, ?)', fila)

    except sqliteDb.Error, e:
        print "Error asociando pois a ruta de museos: %s " % e.args[0]
        sys.exit(1)

print "Creadas relaciones de la ruta de museos"

sqliteDb.commit()






#-----------------------------------------------------------------------------------------
# Cargamos los restaurantes
#
#
#-----------------------------------------------------------------------------------------
print "\nCargando restaurantes..."
filas = []
filas2 = []
clavesPois = []

sql='SELECT * from restaurantes;'
cursorMysql.execute(sql)
resultado=cursorMysql.fetchall()

for registro in resultado:
    columnas = []  
    columnas2 = []
    
    columnas.append(registro[9]) 
    columnas.append(idPoi)
    columnas.append(str(registro[11]))
    columnas.append(str(registro[12]))
    columnas.append(registro[1])
    columnas.append(registro[8])
    columnas.append(RESTAURANTE)
    columnas.append(registro[2])
    columnas.append(registro[4])
    columnas.append(1)
    columnas.append(registro[5])
    
    columnas2.append(idPoi)
    columnas2.append(" ")
    columnas2.append(registro[6])
    columnas2.append(" ")
    columnas2.append(registro[3])
    
    filas.append(columnas)
    filas2.append(columnas2)
    
    clavesPois.append(idPoi);   # nos guardamos las claves de los poi para asociarlos a la ruta
    
    idPoi = idPoi + 1


for fila in filas:
    try:
        cursorSqlite.execute('insert into poi (descPoi, _id, latitud, longitud, nombrePoi, url, categoria, \
            uriImagen, direccion, comercial, telefono) values (?,?,?,?,?,?,?,?,?,?,?)', fila)

    except sqliteDb.Error, e:
        print "Error cargando pois restaurantes: %s" % e.args[0]
        sys.exit(1)

# Damos de alta los datos específicos de un restaurante
for fila2 in filas2:
    try:
        cursorSqlite.execute('insert into restaurante (_id, tipo_cocina, precio_medio, url_reserva, especialidad) \
            values (?,?,?,?,?)', fila2)

    except sqliteDb.Error, e:
        print "Error cargando pois restaurantes(2): %s" % e.args[0]
        sys.exit(1)

sqliteDb.commit()
print "Cargados pois de restaurantes"


#-----------------------------------------------------------------------------------------
# Damos de alta la ruta en la tabla de rutas        
#-----------------------------------------------------------------------------------------
print "\nCreando lista de restaurantes..."

filas = []
filas.append([u'Restaurantes', LISTA_RESTAURANTES, u'Restaurantes', u'C', \
    15, 43.356598, -8.399785])
        
for fila in filas:
    try:
        cursorSqlite.execute('insert into ruta (descRuta, _id, nombreRuta, tipo, default_zoom_level, \
        default_latitude, default_longitude) values (?,?,?,?,?,?,?)', fila)
   
    except sqliteDb.Error, e:
        print "Error creando lista de restaurantes: %s " % e.args[0]
        sys.exit(1)

print "Creada lista de museos"

sqliteDb.commit()


#-----------------------------------------------------------------------------------------
# Damos de alta las relaciones poi/ruta         
#-----------------------------------------------------------------------------------------
print "\nAsociando pois a ruta..."

for clave in clavesPois:
    try:
        fila = (clave, LISTA_RESTAURANTES)
        cursorSqlite.execute('insert into poiRuta (idPoi, idRuta) values (?, ?)', fila)

    except sqliteDb.Error, e:
        print "Error asociando pois a lista de restaurantes: %s " % e.args[0]
        sys.exit(1)

print "Creadas relaciones de la lista de restaurantes"

sqliteDb.commit()






#-----------------------------------------------------------------------------------------
# Cargamos los hoteles
#
#
#-----------------------------------------------------------------------------------------
print "\nCargando hoteles..."
filas = []
filas2 = []
clavesPois = []

sql='SELECT * from hoteles;'
cursorMysql.execute(sql)
resultado=cursorMysql.fetchall()

for registro in resultado:
    columnas = []  
    columnas2 = []
    
    columnas.append('') 
    columnas.append(idPoi)
    columnas.append(str(registro[4]))
    columnas.append(str(registro[5]))
    columnas.append(registro[1])
    columnas.append('')
    columnas.append(HOTEL)
    columnas.append('')
    columnas.append(registro[2])
    columnas.append(1)
    columnas.append(registro[3])
    
    columnas2.append(idPoi)
    estrellas = registro[7]

    columnas2.append(int(estrellas[4:5]))
    columnas2.append("")
    
    filas.append(columnas)
    filas2.append(columnas2)
    
    clavesPois.append(idPoi);   # nos guardamos las claves de los poi para asociarlos a la ruta
    
    idPoi = idPoi + 1


for fila in filas:
    try:
        cursorSqlite.execute('insert into poi (descPoi, _id, latitud, longitud, nombrePoi, url, categoria, \
            uriImagen, direccion, comercial, telefono) values (?,?,?,?,?,?,?,?,?,?,?)', fila)

    except sqliteDb.Error, e:
        print "Error cargando pois hoteles: %s" % e.args[0]
        sys.exit(1)

# Damos de alta los datos específicos de un restaurante
for fila2 in filas2:
    try:
        cursorSqlite.execute('insert into hotel (_id, estrellas, url_booking) \
            values (?,?,?)', fila2)

    except sqliteDb.Error, e:
        print "Error cargando pois hoteles(2): %s" % e.args[0]
        sys.exit(1)

sqliteDb.commit()
print "Cargados pois de hoteles"



#-----------------------------------------------------------------------------------------
# Damos de alta la ruta en la tabla de rutas        
#-----------------------------------------------------------------------------------------
print "\nCreando lista de hoteles..."

filas = []
filas.append([u'Hoteles', LISTA_HOTELES, u'Hoteles', u'C', \
    16, 43.368713, -8.390703])
        
for fila in filas:
    try:
        cursorSqlite.execute('insert into ruta (descRuta, _id, nombreRuta, tipo, default_zoom_level, \
        default_latitude, default_longitude) values (?,?,?,?,?,?,?)', fila)
   
    except sqliteDb.Error, e:
        print "Error creando lista de hoteles: %s " % e.args[0]
        sys.exit(1)

print "Creada lista de hoteles"

sqliteDb.commit()


#-----------------------------------------------------------------------------------------
# Damos de alta las relaciones poi/ruta         
#-----------------------------------------------------------------------------------------
print "\nAsociando pois a ruta..."

for clave in clavesPois:
    try:
        fila = (clave, LISTA_HOTELES)
        cursorSqlite.execute('insert into poiRuta (idPoi, idRuta) values (?, ?)', fila)

    except sqliteDb.Error, e:
        print "Error asociando pois a lista de hoteles: %s " % e.args[0]
        sys.exit(1)

print "Creadas relaciones de la lista de hoteles"

sqliteDb.commit()





mySqlDb.close()
sqliteDb.close()
