
def desencriptacion(msg, MORSE_DICCIONARIO):
    listaClaves = list(MORSE_DICCIONARIO.keys())
    listaValores = list(MORSE_DICCIONARIO.values())
    diccionario = dict()
    
    for i, j in zip(listaClaves, listaValores):
        diccionario[j] = i

    if msg[len(msg) - 1] == " ":
        msg = msg[:-1]

    lista_palabras = msg.split(" / ")
    lista_letras = list()
    mensaje_desencriptado = list()
    
    for i in lista_palabras:
        palabra = ""
        lista_letras = i.split(" ")
        for j in lista_letras:
            if j not in listaValores:

                print(j)
                mensaje_desencriptado.clear()
                mensaje_desencriptado.append('Codigo morse no valido.')
                return mensaje_desencriptado
            palabra = palabra + diccionario[j]
        mensaje_desencriptado.append(palabra)
        palabra = ""
    return mensaje_desencriptado