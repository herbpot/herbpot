import random

sens = []
sensafter = {}

def main() :
    try :
        global senlen, senlenafter
        senlen = int(input('문장 수 입력 >>>'))
        senlenafter = int(input('최종 문장 수 입력 >>>'))
    except :
        main()
    for i in range(senlen) :
        sens.append(input('문장입력 >>>'))
    mixer()

def mixer():
    sentence = ''
    senslen = random.randint(1,senlen)
    e = ''
    
    while True :
        for i in range(senslen):
            s = sens[random.randint(0,senlen-1)]
            if not s == e:
                e = s
                sentence += s
                
                print('create one')
        try:
            sentence[sentence]
        except:
            sensafter[sentence] = True
            if len(list(sensafter.keys())) >= senlenafter :
                break
            
    

    print(list(sensafter.keys()))


main()