# VariablesDB

Плагин переменных. Позволяет читать и записывать значения переменных в ДБ.

Команды:
<ul>
<li><code>/setvariable [key] [value]</code> - записывает в переменную <b>key</b> значение <b>value</b> (доступно только для OP игроков)</li>
<li><code>/getvariable [key]</code> - читает значение переменной <b>key</b> и выводит его</li>
<li><code>/setvariablepass [passkey] [key] [value]</code> - записывает в переменную <b>key</b> значение <b>value</b>, если ключевое слово <b>passkey</b> совпало с ключевым полем в конфиге</li>
<li><code>/variablesdb reload</code> - перезагрузить плагин (а точнее, соединение с БД)</li>
</ul>

Конфиг:
<pre>
# Таблица с полями: key (varchar), value (varchar), datetime (timestamp)
connect:
    enabled: false
    ip: 127.0.0.1
    db: localhost
    login: root
    password: password
    table: variables
    
# ключевое слово, для команды setvariablepass
variablepass: 'qwerty'
</pre>
