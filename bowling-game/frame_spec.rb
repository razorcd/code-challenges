require_relative "frame"

RSpec.describe Frame do
  context "NOT a last frame" do
    let(:frame) { Frame.new }

    context "general" do
      it "should have score 0 at start" do
        expect(frame.score).to eq 0
      end

      it "should update score after a throw" do
        frame.throw! knocked_pins: 5
        expect(frame.score).to eq 5
      end

      it "should not be able to knok more pins then standing" do
        expect { frame.throw! knocked_pins: (Frame::TOTAL_PINS + 1) }.
            to raise_error(FrameError)
      end

      it "should not be over at start or after a throw with less knocks" do
        expect(frame.over?).to eq false
        frame.throw! knocked_pins: 5
        expect(frame.over?).to eq false
      end
    end

    context "knocks only a part of the pins" do
      before :each do
        frame.throw! knocked_pins: 3
        frame.throw! knocked_pins: 4
      end

      it "should be over" do
        expect(frame.over?).to eq true
      end

      it "should return correct score" do
        expect(frame.score).to eq 7
      end

      it "should not accept extra score" do
        expect(frame.update_extra_score(added_points: 5)).to eq false
        expect(frame.score).to eq 7
      end

      it "should not be able to throw again" do
        expect { frame.throw!(knocked_pins: 5) }.to raise_error(FrameError)
      end
    end

    context "throws a spare" do
      before :each do
        frame.throw! knocked_pins: 3
        frame.throw! knocked_pins: (Frame::TOTAL_PINS - 3)
      end

      it "should be over" do
        expect(frame.over?).to eq true
      end

      it "should return correct score" do
        expect(frame.score).to eq Frame::TOTAL_PINS
      end

      it "should accept one extra score" do
        expect(frame.update_extra_score(added_points: 5)).to eq true
        expect(frame.score).to eq (Frame::TOTAL_PINS + 5)
        expect(frame.update_extra_score(added_points: 6)).to eq false
        expect(frame.score).to eq (Frame::TOTAL_PINS + 5)
      end

      it "should not be able to throw again" do
        expect { frame.throw!(knocked_pins: 5) }.to raise_error(FrameError)
      end
    end

    context "throws a strike" do
      before :each do
        frame.throw! knocked_pins: Frame::TOTAL_PINS
      end

      it "should be over" do
        expect(frame.over?).to eq true
      end

      it "should return correct score" do
        expect(frame.score).to eq Frame::TOTAL_PINS
      end

      it "should accept two extra scores" do
        expect(frame.update_extra_score(added_points: 5)).to eq true
        expect(frame.score).to eq (Frame::TOTAL_PINS + 5)
        expect(frame.update_extra_score(added_points: 6)).to eq true
        expect(frame.score).to eq (Frame::TOTAL_PINS + 5 + 6)
        expect(frame.update_extra_score(added_points: 7)).to eq false
        expect(frame.score).to eq (Frame::TOTAL_PINS + 5 + 6)
      end

      it "should not be able to throw again" do
        expect { frame.throw!(knocked_pins: 5) }.to raise_error(FrameError)
      end
    end
  end

  context "IS a last frame" do
    let(:frame) { Frame.new last: true}

    it "should have score 0 at start" do
      expect(frame.score).to eq 0
    end

    it "should allow 2 normal throws" do
      frame.throw! knocked_pins: 3
      frame.throw! knocked_pins: 2
      expect(frame.score).to eq 5
      expect(frame.over?).to eq true
      expect { frame.throw! knocked_pins: 1 }.to raise_error(FrameError)
    end

    it "should allow 3 throws if first is a strike" do
      frame.throw! knocked_pins: 10
      expect(frame.score).to eq 10
      expect(frame.over?).to eq false

      frame.throw! knocked_pins: 2
      expect(frame.score).to eq 12
      expect(frame.over?).to eq false

      frame.throw! knocked_pins: 3
      expect(frame.score).to eq 15
      expect(frame.over?).to eq true
      expect { frame.throw! knocked_pins: 1 }.to raise_error(FrameError)
    end

    it "should allow 3 throws if first 2 are a strike" do
      frame.throw! knocked_pins: 10
      expect(frame.score).to eq 10
      expect(frame.over?).to eq false

      frame.throw! knocked_pins: 10
      expect(frame.score).to eq 20
      expect(frame.over?).to eq false

      frame.throw! knocked_pins: 6
      expect(frame.score).to eq 26
      expect(frame.over?).to eq true
      expect { frame.throw! knocked_pins: 1 }.to raise_error(FrameError)
    end

    it "should allow 3 throws if first 2 are a spare" do
      frame.throw! knocked_pins: 2
      frame.throw! knocked_pins: 8
      expect(frame.score).to eq 10
      expect(frame.over?).to eq false

      frame.throw! knocked_pins: 2
      expect(frame.score).to eq 12
      expect(frame.over?).to eq true
      expect { frame.throw! knocked_pins: 1 }.to raise_error(FrameError)
    end
  end
end
