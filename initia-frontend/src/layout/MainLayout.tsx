import { Outlet } from 'react-router-dom';
import { Home, Users } from 'lucide-react';
import { Link } from 'react-router-dom';

export default function MainLayout() {
  return (
    <div className="min-h-screen bg-gray-50">
      {/* Top Navigation */}
      <header className="bg-white shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-16 items-center">
            <div className="flex items-center">
              <Link to="/" className="text-xl font-bold text-indigo-600">
                INITIA Web
              </Link>
            </div>

            <nav className="flex space-x-8">
              <Link
                to="/clients"
                className="flex items-center text-gray-600 hover:text-indigo-600 transition-colors"
              >
                <Users className="w-5 h-5 mr-1" />
                Clients
              </Link>
              {/* Add Assurances link later */}
            </nav>
          </div>
        </div>
      </header>

      {/* Main Content */}
      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <Outlet />
      </main>
    </div>
  );
}