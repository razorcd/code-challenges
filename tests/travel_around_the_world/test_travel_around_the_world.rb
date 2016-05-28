require 'test/unit'
require_relative '../../travel_around_the_world/travel_around_the_world'

class TravelAroundTheWorldTest < Test::Unit::TestCase
  def travel
    Travel.new cities: [3,1,2], gallons: [2,2,2]
  end

  def test_initialize
    assert_equal(defined?(travel).!.!, true)
  end

  def test_possible_starting_cities_count
    assert_equal(travel.possible_starting_cities_count, 2)
  end
end
