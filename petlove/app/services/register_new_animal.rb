class RegisterNewAnimal
  def self.run params
    person = Person.find(params[:person_id])

    if person.nil?
      raise "person with id #{params[:person_id]} not found"
    end

    animal = Animal.new(
      name: params[:name],
      monthly_cost: params[:monthly_cost],
      atype: params[:type],
      person: person
    )

    begin
      if person.is_allowed_to_own? animal
        if animal.save
          return animal
        else
          raise "something has happened, try again later."
        end
      end
    rescue => exception
      raise exception.message
    end
  end
end
