class AnimalsController < ApplicationController
  def create
    begin
      new_animal = RegisterNewAnimal.run params
      render json: new_animal, status: :created
    rescue => exception
      render json: { error: exception.message }, status: :bad_request
    end
  end
end
