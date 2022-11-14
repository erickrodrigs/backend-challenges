class CreateAnimals < ActiveRecord::Migration[7.0]
  def change
    create_table :animals do |t|
      t.string :name
      t.float :monthly_cost
      t.string :atype
      t.references :person, null: false, foreign_key: true

      t.timestamps
    end
  end
end
