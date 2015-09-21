def show = { println it }
def shout = {
    def s = it as String
    println s.toUpperCase()
}

def square_root = { Math.sqrt(it) }
def sum = {a, b -> a + b }

def please(action) {
    [
        the: { what ->
            [
                of: { n ->
                    action(what(n))
                },
                ofTwo: { a, b ->
                    action(what(a, b))
                }
            ]
        }
    ]
}

please show the square_root of 1024
please shout the sum ofTwo 1024, 10
