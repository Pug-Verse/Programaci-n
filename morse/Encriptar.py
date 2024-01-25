
def encriptacion(msg, MORSE_DICCIONARIO):
    lista1 = list(MORSE_DICCIONARIO.keys())
    lista_palabras = msg.split(" ")
    mensaje_cifrado = list()
    morse_palabra = ""
    for i in lista_palabras:
        for j in i:
            if j.upper() not in lista1:

                mensaje_cifrado.clear()
                mensaje_cifrado.append(
                    "No se puede traducir a codigo morse")
                return mensaje_cifrado
            morse_palabra = morse_palabra + MORSE_DICCIONARIO[j.upper()]
            morse_palabra = morse_palabra + " "
        mensaje_cifrado.append(morse_palabra)
        morse_palabra = ""
    return mensaje_cifrado


if __name__ == "__main__":
    MORSE_DICCIONARIO = {'A': '.-', 'B': '-...',
                       'C': '-.-.', 'D': '-..', 'E': '.',
                       'F': '..-.', 'G': '--.', 'H': '....',
                       'I': '..', 'J': '.---', 'K': '-.-',
                       'L': '.-..', 'M': '--', 'N': '-.',
                       'Ñ': '-.-.--', 'O': '---', 'P': '.--.', 'Q': '--.-',
                       'R': '.-.', 'S': '...', 'T': '-',
                       'U': '..-', 'V': '...-', 'W': '.--',
                       'X': '-..-', 'Y': '-.--', 'Z': '--..',
                       '1': '.----', '2': '..---', '3': '...--',
                       '4': '....-', '5': '.....', '6': '-....',
                       '7': '--...', '8': '---..', '9': '----.',
                       '0': '-----', ', ': '--..--', '.': '.-.-.-',
                       '?': '..--..', '/': '-..-.', '-': '-....-',
                       '(': '-.--.', ')': '-.--.-'}
    e = encriptacion('', MORSE_DICCIONARIO)
   