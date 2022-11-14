class Person < ApplicationRecord
  has_many :animals

  validates :name, presence: true
  validates :document, presence: true
  validates :birthdate, presence: true

  def is_allowed_to_own? animal
    if exceeded_total_monthly_cost?
      raise 'person has total monthly cost with animals greater than 1000'
    end

    if animal.atype == 'Andorinha' and not is_allowed_to_own_swallows?
      raise 'person is not allowed to own swallows'
    elsif animal.atype == 'Gato' and not is_allowed_to_own_cats?
      raise 'person is not allowed to own cats'
    end

    return true
  end
  
  private

  def exceeded_total_monthly_cost?
    total_monthly_cost > 1000
  end

  def total_monthly_cost
    self.animals.map { |animal| animal.monthly_cost }.sum
  end

  def is_allowed_to_own_swallows?
    ((Date.today - self.birthdate) / 365.25).floor >= 18
  end

  def is_allowed_to_own_cats?
    self.name[0] != 'A'
  end  
end
