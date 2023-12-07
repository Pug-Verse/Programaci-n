import os
import ipaddress
import socket

ips_encontradas=[]

red=input("Introduce una red en formato CIDR: ")
puerto_ini=int(input("Introduce puerto inicial: ")) 
puerto_fin=int(input("Introduce puerto final: "))
ips=ipaddress.IPv4Network(red).hosts()

for ip in ips:
    print("\rEscaneando la IP: ",ip, end="")
    comando="ping -c 1 -W 0.01 " +str(ip)+" > /dev/null"
    respuesta=os.system(comando)
    if(respuesta==0):
        print(" ---> Live host")
        ips_encontradas.append(str(ip))
print()
print(ips_encontradas)


for ip in ips_encontradas:
    print("\nEscanenado la IP: ",red)

    for i in range(puerto_ini,puerto_fin+1):
        print("\rEscaneando puerto: ",i, end="")
    # Creamos un socket IPv4 de tipo TCP
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # Establecemos un tiempo de espera máximo de 100 ms
        s.settimeout(0.1)
    # Tratamos de establecer la conexión
        respuesta = s.connect_ex((ip,i))
        if(respuesta==0):
            print(" ---> Puerto abierto:")
    # Cerramos el socket
        s.close()
    #print(respuesta)