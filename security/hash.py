from Crypto.Hash import SHA256
message1 = b'Hash Test Message 1'
message2 = b'Hash Test Message 2'
message3 = b'Hash Test Message 3'
message4 = '해시함수 테스트 1'
h = SHA256.new()
h.update(message1)
print(h.hexdigest())
h = SHA256.new()
h.update(message2)
print(h.hexdigest())
h = SHA256.new()
h.update(message3)
print(h.hexdigest())
h = SHA256.new()
h.update(message4.encode('utf-8'))
print(h.hexdigest())