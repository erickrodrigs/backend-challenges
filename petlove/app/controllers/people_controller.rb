class PeopleController < ApplicationController
  def create
    begin
      new_person = RegisterNewPerson.run params
      render json: new_person, status: :created
    rescue => exception
      render json: { error: exception.message }, status: :bad_request
    end
  end
end
