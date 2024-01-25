import Encriptar
import Desencriptar

MORSE_DICCIONARIO = {
    # ----------Alfabeto----------
    'A': '.-',
    'B': '-...',
    'C': '-.-.',
    'D': '-..',
    'E': '.',
    'F': '..-.',
    'G': '--.',
    'H': '....',
    'I': '..',
    'J': '.---',
    'K': '-.-',
    'L': '.-..',
    'M': '--',
    'N': '-.',
    'Ñ': '-.-.--',
    'O': '---',
    'P': '.--.',
    'Q': '--.-',
    'R': '.-.',
    'S': '...',
    'T': '-',
    'U': '..-',
    'V': '...-',
    'W': '.--',
    'X': '-..-',
    'Y': '-.--',
    'Z': '--..',
    # ----------Numeros----------
    '0': '-----',
    '1': '.----',
    '2': '..---',
    '3': '...--',
    '4': '....-',
    '5': '.....',
    '6': '-....',
    '7': '--...',
    '8': '---..',
    '9': '----.',
    # ----------Caracteres----------
    '.': '.-.-.-',
    ',': '--..--',
    '?': '..--..',
    "'": '.----.',
    '!': '-.-.--',
    '/': '-..-.',
    '(': '-.--.',
    ')': '-.--.-',
    '&': '.-...',
    ':': '---...',
    ';': '-.-.-.',
    '=': '-...-',
    '+': '.-.-.',
    '-': '-....-',
    '_': '..--.-',
    '"': '.-..-.',
    '$': '...-..-',
    '@': '.--.-.',
    '¿': '..-.-',
    '¡': '--...-'
    }

if __name__ == "__main__":
    while True:
        print("\n\033[4mTraduccion Codigo Morse\033[0m\n")
        print("1. Traducir de texto a morse")
        print("2. Traducir de morse a texto")
        print("3. Salir\n")
        opcion = input("Opcion: ")
        
        if opcion == '1':
            z = input("Introduce texto a convertir a morse: ")
            msg_encriptado = Encriptar.encriptacion(z, MORSE_DICCIONARIO)
            palabra_encriptada = ""
            for i in msg_encriptado:
                palabra_encriptada = palabra_encriptada + i + "/ "
            palabra_encriptada = palabra_encriptada[:-2]
            print("Codigo encriptado --> " + palabra_encriptada)

        elif opcion == '2':
            z = input("Introduce codigo a convertir a texto: ")
            msg_desencriptado = Desencriptar.desencriptacion(z, MORSE_DICCIONARIO)
            d_string = ""
            for i in msg_desencriptado:
                d_string = d_string + i + ' '
            d_string = d_string[:-1]
            print(d_string)

        elif opcion == '3':
            break

        else:
            print("Opcion invalida. Intente de nuevo")
            pass