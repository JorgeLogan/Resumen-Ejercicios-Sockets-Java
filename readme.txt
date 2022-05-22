Ejercicio sencillo de como funcionar con tcp, udp y multicast.

Ejercicio TCP
-------------
Los clientes se conectaran al servior y enviaran mensajes, que recibiran del servidor con un OK seguido
del mensaje enviado.
Si envian un mensaje "adios" avisaran al servidor de que finalizan su sesion, y luego se cierran

El servidor recibira un maximo de 3 clientes, luego dejara de estar a la escucha. 
Cuando recibe un cliente, crea un hilo para el, en el que se pone a la escucha de sus mensajes.
Al recibir los mensajes, enviara de vuelta OK mas el mismo mensaje que leyo.
Si recibe un mensaje de "adios" finalizará el hilo
El programa acabará cuando finalizen los hilos de los 3 clientes.


Ejercicio UDP
-------------
El emisor UDP cogera mensajes por consola y los enviara a un receptor UDP.
Si el mensaje es 'adios' lo enviará y finalizará su programa

El receptor UDP escuchará mensajes del emisor, y los mostrará en pantalla. Si el mensaje
es 'adios' finalizará su programa


Ejercicio Multicast
-------------------

Funcionamiento igual que el ejercicio UDP pero con Multicast
