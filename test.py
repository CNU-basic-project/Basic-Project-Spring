import torch, sys
from torch import nn
import numpy as np
import pickle

class CustomModel(nn.Module):
    def __init__(self):
        super(CustomModel, self).__init__()
        self.layer = nn.Sequential(
            nn.Linear(5, 64),
            nn.BatchNorm1d(64),
            nn.ReLU(),
            nn.Dropout(0.5),
            nn.Linear(64, 32),
            nn.BatchNorm1d(32),
            nn.ReLU(),
            nn.Dropout(0.5),
            nn.Linear(32, 16),
            nn.BatchNorm1d(16),
            nn.ReLU(),
            nn.Dropout(0.3),
            nn.Linear(16, 4),
            nn.Softmax(dim=1)
        )

    def forward(self, x):
        x = self.layer(x)
        return x

device = "cuda" if torch.cuda.is_available() else "cpu"
model = CustomModel().to(device)
model.load_state_dict(torch.load('model_state_dict.pth'))

with open('mean_std.pkl', 'rb') as f:
    mean, std = pickle.load(f)

mapping = ["경계", "주의", "보통", "낮음"]

def normalization(data):
    new_data = []
    for i in range(len(mean)):
        new_data.append((data[i]-mean[i])/std[i])
    return new_data

x1 = float(sys.argv[1])
x2 = float(sys.argv[2])
x3 = float(sys.argv[3])
x4 = float(sys.argv[4])
x5 = float(sys.argv[5])

with torch.no_grad():
    model.eval()
    data = [x1, x2, x3, x4, x5]
    new_data = normalization(data)
    
    inputs = torch.FloatTensor(
        [new_data]
    ).to(device)
    output = model(inputs)
    _, predicted = torch.max(output.data, 1)

    print(mapping[predicted])