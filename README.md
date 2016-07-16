# Código do Curso de Mapeamento Objeto Relacional com Hibernate

Este projeto contém o código fonte dos exemplos presentes nos Slids da disciplina. Para melhor organização os commits foram realizados de forma sequencial, para visualizar o código em uma situação específica do curso deve-se realizar o checkout para o commit desejado.

Exemplo:

    $ git log
    .....
    commit 2ab2ef752904fd9f24b79c1951cb07dbdebd53c9
    Author: Lucas Oliveira <luksrn@gmail.com>
    Date:   Sun Jun 19 14:08:27 2016 -0300
    06 - Mapeamento de Enum com JPA 2.1 Converters

    commit f02216d5ee0932a727208566b9f60e169b3b4f58
    Author: Lucas Oliveira <luksrn@gmail.com>
    Date:   Fri Jun 17 01:03:52 2016 -0300
    05 - Mapeamento de herança - Single Table e Consultas polimorficas

    commit 1fed8087eced32705478cd4a7f91d17a1ee509ce
    Author: Lucas Oliveira <luksrn@gmail.com>
    Date:   Thu Jun 16 23:55:36 2016 -0300
    04 - Versão inicial dos mapeamentos entre relações One To Many Many To One e Many To Many
    ......


Para retornar na versão de mapeamento de herança, deve-se criar uma detached branch:

    $ git branch
    * master
    $ git checkout f02216d5ee0932a727208566b9f60e169b3b4f58
    HEAD is now at f02216d... 05 - Mapeamento de herança - Single Table e Consultas polimorficas
    $ git branch
    * (HEAD detached at f02216d)
      master
  
  
Para retornar o projeto para versão mais recente:

    $ git checkout master
    Previous HEAD position was f02216d... 05 - Mapeamento de herança - Single Table e Consultas polimorficas
    Switched to branch 'master'
