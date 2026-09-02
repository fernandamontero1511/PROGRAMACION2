class EcuacionLineal:
    def __init__(self, a: float, b: float, c: float, d: float, e: float, f: float):
        self.__a = a
        self.__b = b
        self.__c = c
        self.__d = d
        self.__e = e
        self.__f = f

    def tieneSolucion(self) -> bool:
        # Devuelve True si ad - bc no es cero
        return (self.__a * self.__d - self.__b * self.__c) != 0

    def getX(self) -> float:
        denominador = self.__a * self.__d - self.__b * self.__c
        return (self.__e * self.__d - self.__b * self.__f) / denominador

    def getY(self) -> float:
        denominador = self.__a * self.__d - self.__b * self.__c
        return (self.__a * self.__f - self.__e * self.__c) / denominador


# Programa de prueba (Test)
if __name__ == "__main__":
    entrada = input("Ingrese a, b, c, d, e, f: ")
    valores = list(map(float, entrada.split()))
    
    a, b, c, d, e, f = valores
    ecuacion = EcuacionLineal(a, b, c, d, e, f)

    if ecuacion.tieneSolucion():
        print(f"x = {ecuacion.getX():.1f}, y = {ecuacion.getY():.1f}")
    else:
        print("La ecuación no tiene solución")