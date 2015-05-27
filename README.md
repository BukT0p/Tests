# Tests
Just a simple project to test simple things

Byte Code for simple for:

aload_1
invokeinterface java/util/List/size()I 1
istore_2
iconst_0
istore_3
iconst_0
istore 4
iload 4
iload_2
if_icmpge 21
iload_3
aload_1
iload 4
invokeinterface java/util/List/get(I)Ljava/lang/Object; 2
checkcast java/lang/String
invokevirtual java/lang/String/length()I
iadd
istore_3
iinc 4 1
goto 8
iload_3
ireturn

Byte Code for ForEach:
iconst_0
istore_2
aload_1
invokeinterface java/util/List/iterator()Ljava/util/Iterator; 1
astore_3
aload_3
invokeinterface java/util/Iterator/hasNext()Z 1
ifeq 17
iload_2
aload_3
invokeinterface java/util/Iterator/next()Ljava/lang/Object; 1
checkcast java/lang/String
invokevirtual java/lang/String/length()I
iadd
istore_2
goto 6
iload_2
ireturn
