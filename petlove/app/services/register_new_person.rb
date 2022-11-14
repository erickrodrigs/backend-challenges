class RegisterNewPerson
  def self.run params
    Person.create(
      name: params[:name],
      document: params[:document],
      birthdate: Date.parse(params[:birthdate])
    )
  end
end
