from random import randrange


def tablero(board):

	print("+-------" * 3,"+", sep="")
	for fila in range(3):
		print("|       " * 3,"|", sep="")
		for columna in range(3):
			print("|   " + str(board[fila][columna]) + "   ", end="")
		print("|")
		print("|       " * 3,"|",sep="")
		print("+-------" * 3,"+",sep="")

def mostrar_menu():
    print("\n       \033[4mMenú\033[0m\n")
    print("1. Nueva Partida")
    print("2. Ver Marcador")
    print("3. Salir")


def hacer_movimiento(board):

	flag = False	# suposición falsa - la necesitamos para entrar en el bucle
	while not flag:
		movimiento = input("\nIngresa tu movimiento: ") 
		flag = len(movimiento) == 1 and movimiento >= '1' and movimiento <= '9' # ¿es valido lo que ingreso el usuario?
		if not flag:
			print("\nMovimiento erróneo, ingrésalo nuevamente") # no, no lo es. ingrésalo nuevamente
			continue
		movimiento = int(movimiento) - 1 	# numero de la celda, del 0 al 8
		fila = movimiento // 3 	# fila de la celda
		columna = movimiento % 3		# columna de la celda
		signo = board[fila][columna]	# marca el cuadro elegido
		flag = signo not in ['\033[;32m'+'O'+'\033[0;m','\033[;31m'+'X'+'\033[0;m'] 
		if not flag:	# esta ocupado, ingresa una posición nuevamente
			print("\n¡Cuadro ocupado, ingresa nuevamente!")
			continue
	board[fila][columna] = '\033[;32m'+'O'+'\033[0;m' 	# colocar '0' al cuadro seleccionado


def matriz_Cuadrosvacios(board):
	lista_vacia = []	# la lista esta vacía inicialmente
	for fila in range(3): # itera a través de las filas
		for columna in range(3): # itera a través de las columnas
			if board[fila][columna] not in ['\033[;32m'+'O'+'\033[0;m','\033[;31m'+'X'+'\033[0;m']: # ¿Está la celda libre?
				lista_vacia.append((fila,columna)) # si, agrega una nueva tupla a la lista
	return lista_vacia


def partida_ganada(board,sgn):
	if sgn == '\033[;31m'+'X'+'\033[0;m':	# ¿Estamos buscando X?
		who = 'maquina'	# Es la maquina
	elif sgn == '\033[;32m'+'O'+'\033[0;m': # ... ¿o estamos buscando O?
		who = 'jugador'	# Es el usuario
	else:
		who = None	# ¡No debemos de caer aquí!
	diagonal1 = diagonal2 = True  # para las diagonales
	for rc in range(3):
		if board[rc][0] == sgn and board[rc][1] == sgn and board[rc][2] == sgn:	# comprueba fila rc
			return who
		if board[0][rc] == sgn and board[1][rc] == sgn and board[2][rc] == sgn: # comprueba columna rc
			return who
		if board[rc][rc] != sgn: # revisar la primer diagonal
			diagonal1 = False
		if board[2 - rc][2 - rc] != sgn: # revisar la segunda diagonal
			diagonal2 = False
	if diagonal1 or diagonal2:
		return who
	return None


def partida_empatada(board):
	lista_vacia = matriz_Cuadrosvacios(board) # crea una lista de los cuadros vacios o libres
	contador = len(lista_vacia)
	if contador > 0:	# si la lista no esta vacía, elegir un lugar para 'X' y colocarla
		this = randrange(contador)
		fila, columna = lista_vacia[this]
		board[fila][columna] = '\033[;31m'+'X'+'\033[0;m'

cont_victorias=0
cont_derrotas=0

def marcador_victoria():
	global cont_victorias
	cont_victorias=cont_victorias+1

def marcador_derrota():
	global cont_derrotas
	cont_derrotas=cont_derrotas+1

def marcador_empate():
	global cont_victorias
	global cont_derrotas
	cont_victorias=cont_victorias+0.5
	cont_derrotas=cont_derrotas+0.5


def reiniciar_marcador():
	global cont_victorias
	global cont_derrotas
	cont_victorias=0
	cont_derrotas=0


print("\n\n++++++++++++++++++++++++++++++++++++++\n               3 EN RAYA\n++++++++++++++++++++++++++++++++++++++\n")

board = [ [3 * j + i + 1 for i in range(3)] for j in range(3) ] # crear un tablero vacío
board[1][1] = '\033[;31m'+'X'+'\033[0;m' # colocar la primer 'X' en el centro
lista_vacia = matriz_Cuadrosvacios(board)
turno_jugador = True # ¿De quien es turno ahora?
salir=0


while salir!=1:
	 
	mostrar_menu()
		
	try:
		
		opcion = int(input("Selecciona una opción: "))
		print()

		if opcion == 1:
			
			while len(lista_vacia):

				tablero(board)
				if turno_jugador:
					hacer_movimiento(board)
					victoria = partida_ganada(board,'\033[;32m'+'O'+'\033[0;m')
				else:	
					partida_empatada(board)
					victoria = partida_ganada(board,'\033[;31m'+'X'+'\033[0;m')
				if victoria != None:
					break
				turno_jugador = not turno_jugador		
				lista_vacia = matriz_Cuadrosvacios(board)

			tablero(board)

			if victoria == 'jugador':
				
				if(cont_victorias==0):
					cont_victorias=1
				else:
			
					marcador_victoria()
				
				print("      ¡Jugador gana!\n")

			elif victoria == 'maquina':
				if(cont_derrotas==0):
					cont_derrotas=1
				else:
					
					marcador_derrota()

				print("      ¡Máquina gana!\n")

			else:

				marcador_empate()
				print("        ¡Empate!\n")

			board = [ [3 * j + i + 1 for i in range(3)] for j in range(3) ] # crear un tablero vacío
			board[1][1] = '\033[;31m'+'X'+'\033[0;m' # colocar la primer 'X' en el centro
			lista_vacia = matriz_Cuadrosvacios(board)
			turno_jugador = True # ¿De quien es turno ahora?				

		elif opcion == 2:
			print("\n        Marcador")
			print("------------------------\n")
			print("      \033[92mJugador: ",cont_victorias,"\033[0m")
			print("      \033[91mMáquina: ",cont_derrotas,"\033[0m")
			print("\n------------------------\n")

		elif opcion == 3:
			reiniciar_marcador()
			salir=1
			print("\nHasta la próxima\n")
			break
		else:
			print("Opción no válida. Inténtalo de nuevo.")

	except ValueError:
		print("\nError: Ingresa un número entero.")



			
