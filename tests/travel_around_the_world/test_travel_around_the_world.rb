require 'test/unit'
require_relative '../../travel_around_the_world/travel_around_the_world'

class TravelAroundTheWorldTest < Test::Unit::TestCase
  def travel1
    Travel.new fill: [3,1,2], consume: [2,2,2]
  end

  def travel2
    Travel.new fill: [3,3,0], consume: [2,2,2]
  end

  def travel3
    Travel.new fill: [3,2,3,5,4,3], consume: [1,4,4,1,2,4]
  end

  def test_initialize
    assert_equal(defined?(travel1).!.!, true)
  end

  def test_possible_starting_cities_count_1
    assert_equal(travel1.possible_starting_cities_count_if(tank_capacity: 3), 2)
    assert_equal(travel1.possible_starting_cities_count_if(tank_capacity: 2), 0)
  end

  def test_possible_starting_cities_count_2
    assert_equal(travel2.possible_starting_cities_count_if(tank_capacity: 2), 0)
  end

  def test_possible_starting_cities_count_3
    assert_equal(travel3.possible_starting_cities_count_if(tank_capacity: 6), 2)
    assert_equal(travel3.possible_starting_cities_count_if(tank_capacity: 4), 0)
  end
end
