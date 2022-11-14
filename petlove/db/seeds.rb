# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the bin/rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: "Star Wars" }, { name: "Lord of the Rings" }])
#   Character.create(name: "Luke", movie: movies.first)
johnny_cash = Person.create(name: 'Johnny Cash', document: '555555555', birthdate: Date.new(1932, 2, 26))
sid_vicious = Person.create(name: 'Sid Vicious', document: '555555555', birthdate: Date.new(1957, 5, 10))
axl_rose = Person.create(name: 'Axl Rose', document: '555555555', birthdate: Date.new(1962, 2, 6))
joey_ramone = Person.create(name: 'Joey Ramone', document: '555555555', birthdate: Date.new(1951, 5, 19))
bruce_dickinson = Person.create(name: 'Bruce Dickinson', document: '555555555', birthdate: Date.new(1958, 8, 7))
kurt_cobain = Person.create(name: 'Kurt Cobain', document: '555555555', birthdate: Date.new(1967, 2, 20))
elvis_presley = Person.create(name: 'Elvis Presley', document: '555555555', birthdate: Date.new(2008, 8, 17))

Animal.create([
  { name: 'Pé de Pano', monthly_cost: 199.99, atype: 'Cavalo', person: johnny_cash },
  { name: 'Rex', monthly_cost: 99.99, atype: 'Cachorro', person: sid_vicious },
  { name: 'Ajudante do Papai Noel', monthly_cost: 99.99, atype: 'Cachorro', person: axl_rose },
  { name: 'Rex', monthly_cost: 103.99, atype: 'Papagaio', person: joey_ramone },
  { name: 'Flora', monthly_cost: 103.99, atype: 'Lhama', person: bruce_dickinson },
  { name: 'Dino', monthly_cost: 177.99, atype: 'Iguana', person: kurt_cobain },
  { name: 'Lassie', monthly_cost: 407.99, atype: 'Ornitorrinco', person: elvis_presley }
])
